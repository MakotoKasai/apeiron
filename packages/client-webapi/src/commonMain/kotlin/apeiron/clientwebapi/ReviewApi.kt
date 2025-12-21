package apeiron.clientwebapi

import apeiron.core.review.ReviewDto


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ReviewApi(
    private val http: HttpClient,
    private val baseUrl: String,
) {
    suspend fun listReviews(): List<ReviewDto> =
        http.get("$baseUrl/reviews").body()
}