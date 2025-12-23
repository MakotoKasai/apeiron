package apeiron.infra.mobile.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import apeiron.core.paragraph.Paragraph

@Dao
interface ParagraphDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: ParagraphEntity): Long

    @Query("SELECT * FROM paragraphs WHERE noteId IS NULL ORDER BY createdAtMillis ASC")
    suspend fun listUnassigned(): List<ParagraphEntity>

    @Query("SELECT * FROM paragraphs WHERE noteId = :noteId ORDER BY createdAtMillis ASC")
    suspend fun listByNote(noteId: String): List<ParagraphEntity>

    @Query("UPDATE paragraphs SET noteId = :noteId, updatedAtMillis = :updatedAtMillis WHERE id = :id")
    suspend fun moveToNote(id: String, noteId: String?, updatedAtMillis: Long): Int

    @Query("UPDATE paragraphs SET text = :newText, updatedAtMillis = :updatedAtMillis WHERE id = :id")
    suspend fun updateText(id: String, newText: String, updatedAtMillis: Long): Int

    @Query("DELETE FROM paragraphs WHERE id = :id")
    suspend fun delete(id: String): Int
}