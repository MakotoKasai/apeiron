package apeiron.core.paragraph


import kotlinx.datetime.Instant

class ParagraphUseCase(
    private val repository: ParagraphRepository,
) {
    suspend fun add(id: String, text: String, noteId: String? = null) {
        val epochMillis = System.currentTimeMillis()
        val instantFromMillis: Instant = Instant.fromEpochMilliseconds(epochMillis)
        repository.add(
            Paragraph(
                id = id,
                noteId = noteId,
                text = text,
                createdAt = instantFromMillis,
                updatedAt = instantFromMillis,
            )
        )
    }

    suspend fun listUnassigned() = repository.listUnassigned()
    suspend fun listByNote(noteId: String) = repository.listByNote(noteId)
    suspend fun moveToNote(id: String, noteId: String?) = repository.moveToNote(id, noteId)
    suspend fun updateText(id: String, newText: String) = repository.updateText(id,newText)
    suspend fun delete(id: String) = repository.delete(id)
}