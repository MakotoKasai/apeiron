package apeiron.infra.mobile.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "paragraphs",
    indices = [Index(*["noteId"])]
)
data class ParagraphEntity(
    @PrimaryKey val id: String,
    val noteId: String?,
    val text: String,
    val createdAtMillis: Long,
    val updatedAtMillis: Long,
)