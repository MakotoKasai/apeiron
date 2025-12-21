package apeiron.core.paragraph

interface ParagraphRepository {
    suspend fun add(paragraph: Paragraph)
    suspend fun listUnassigned(): List<Paragraph>
    suspend fun listByNote(noteId: String): List<Paragraph>
    suspend fun moveToNote(id: String, noteId: String?)
    suspend fun updateText(id: String, newText: String)
    suspend fun delete(id: String)
}