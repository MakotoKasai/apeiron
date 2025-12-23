package apeiron.infra.mobile

import apeiron.core.paragraph.Paragraph
import apeiron.core.paragraph.ParagraphRepository
import apeiron.infra.mobile.db.ParagraphDao
import apeiron.infra.mobile.db.ParagraphEntity
import kotlinx.datetime.Instant

class ParagraphRepositoryRoom(
    private val dao: ParagraphDao
) : ParagraphRepository {
    override suspend fun add(paragraph: Paragraph) {
        dao.insert(
            ParagraphEntity(
                id = paragraph.id,
                noteId = paragraph.noteId,
                text = paragraph.text,
                createdAtMillis = paragraph.createdAtMillis.toEpochMilliseconds(),
                updatedAtMillis = paragraph.updatedAtMillis.toEpochMilliseconds(),
            )
        )
    }

    override suspend fun listUnassigned(): List<Paragraph> =
        dao.listUnassigned().map {
            Paragraph(
                id = it.id,
                noteId = it.noteId,
                text = it.text,
                createdAtMillis = Instant.fromEpochMilliseconds(it.createdAtMillis),
                updatedAtMillis = Instant.fromEpochMilliseconds(it.updatedAtMillis),
            )
        }

    override suspend fun listByNote(noteId: String): List<Paragraph> =
        dao.listByNote(noteId).map {
            Paragraph(
                id = it.id,
                noteId = it.noteId,
                text = it.text,
                createdAtMillis = Instant.fromEpochMilliseconds(it.createdAtMillis),
                updatedAtMillis = Instant.fromEpochMilliseconds(it.updatedAtMillis),
            )
        }

    override suspend fun moveToNote(id: String, noteId: String?) {
        val now = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
        dao.moveToNote(id, noteId, now)
    }

    override suspend fun updateText(id: String, newText: String) {
        val now = kotlinx.datetime.Clock.System.now().toEpochMilliseconds()
        dao.updateText(id, newText, now)
    }

    override suspend fun delete(id: String) {
        dao.delete(id)
    }

}