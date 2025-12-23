package apeiron.core.review

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ReviewPostRequest(
    val reviewerName: String,
    val targetId: String,     // WorkId / ProjectId など。まずは文字列でOK
    val body: String
)

@Serializable
data class ReviewDto(
    val id: String,
    val reviewerName: String,
    val targetId: String,
    val body: String,
    val createdAt: Instant
)