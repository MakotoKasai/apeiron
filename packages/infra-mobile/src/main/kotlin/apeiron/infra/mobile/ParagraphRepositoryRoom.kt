package apeiron.infra.mobile


class ParagraphRepositoryRoom(
    private val dao: ParagraphDao
) : ParagraphRepository {
    override suspend fun add(paragraph: Paragraph) {
        dao.insert(
            ParagraphEntity(
                id = paragraph.id,
                noteId = paragraph.noteId,
                text = paragraph.text,
                createdAt = paragraph.createdAt,
                updatedAt = paragraph.updatedAt,
            )
        )
    }

    override suspend fun listByNote(noteId: String): List<Paragraph> =
        dao.listByNote(noteId).map {
            Paragraph(
                id = it.id,
                noteId = it.noteId,
                text = it.text,
                createdAt = Instant.fromEpochMilliseconds(it.createdAtMillis),
                updatedAt = Instant.fromEpochMilliseconds(it.updatedAtMillis),
            )
        }

    override suspend fun updateText(id: String, newText: String) {
        val now = kotlin.time.Clock.System.now().toEpochMilliseconds()
        dao.updateText(id, newText, now)
    }

    override suspend fun delete(id: String) {
        dao.delete(id)
    }

}