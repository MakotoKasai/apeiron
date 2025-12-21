package apeiron.server

import apeiron.core.review.ReviewDto
import apeiron.core.review.ReviewPostRequest
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import java.util.UUID
import kotlin.time.Clock

fun main() {
    val store = mutableListOf<ReviewDto>()

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) { json() }

        routing {
            get("/health") { call.respondText("ok") }

            get("/reviews") {
                call.respond(store.toList())
            }

            post("/reviews") {
                val req = call.receive<ReviewPostRequest>()
                val dto = ReviewDto(
                    id = UUID.randomUUID().toString(),
                    reviewerName = req.reviewerName,
                    targetId = req.targetId,
                    body = req.body,
                    createdAt = Clock.System.now(),
                )
                store.add(dto)
                call.respond(dto)
            }
        }
    }.start(wait = true)
}