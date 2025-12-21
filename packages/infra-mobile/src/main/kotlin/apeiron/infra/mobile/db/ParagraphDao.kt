package apeiron.infra.mobile.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnCofictStrategy
import androidx.room.Query

@Dao
interface ParagraphDao {
    @Insert(onConflict = onConflictStrategy.AORT)
    suspend fun insert(entity: ParagraphEntity)

    @Query("SELECT * FROM paragraphs WHERE noteId IS NULL ORDER BY createdAtMillis ASC")
    suspend fun listUnassigned(): List<Paragraph>

    @Query("SELECT * FROM paragraphs WHERE noteId = :noteId ORDER BY createdAtMillis ASC")
    suspend fun listByNote(noteId: String): List<Paragraph>

    @Query("UPDATE paragraphs SET noteId = :noteId, updatedAtMillis = :updatedAtMillis WHERE id = :id")
    suspend fun MoveToNote(id: String, noteId: String?, updatedAtMillis: Long)

    @Query("UPDATE paragraphs SET text = :newText, updatedAtMillis = :updatedAtMillis WHERE id = :id")
    suspend fun updateText(id: String, newText: String, updatedAtMillis: Long))

    @Query("DELETE FROM paragraphs WHERE id = :id")
    suspend fun delete(id: String)
}