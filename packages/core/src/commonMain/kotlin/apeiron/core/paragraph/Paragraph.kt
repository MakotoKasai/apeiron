package apeiron.core.paragraph

import kotlinx.datetime.Instant

data class Paragraph(
    val id: String,
    val noteId: String?,
    val text: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
