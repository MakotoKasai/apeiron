package apeiron.infra.mobile

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import apeiron.core.paragraph.Paragraph
import apeiron.infra.mobile.db.ApeironDatabase
import apeiron.infra.mobile.db.ParagraphDao
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ParagraphRepositoryRoomTest {

    private lateinit var db: ApeironDatabase
    private lateinit var dao: ParagraphDao
    private lateinit var repo: ParagraphRepositoryRoom

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, ApeironDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.paragraphDao()
        repo = ParagraphRepositoryRoom(dao)
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun add_list_move_update_delete_flow() = runBlocking {
        val now: Instant = Clock.System.now()

        // 1) add (Domain -> DB)
        repo.add(
            Paragraph(
                id = "p1",
                noteId = null,
                text = "hello",
                createdAtMillis = now,
                updatedAtMillis = now
            )
        )

        // 2) listUnassigned (DB -> Domain)
        val unassigned = repo.listUnassigned()
        assertEquals(1, unassigned.size)
        assertEquals("hello", unassigned[0].text)
        assertEquals(null, unassigned[0].noteId)

        // 3) moveToNote
        repo.moveToNote(id = "p1", noteId = "n1")

        val byNote = repo.listByNote("n1")
        assertEquals(1, byNote.size)
        assertEquals("p1", byNote[0].id)

        // 4) updateText
        repo.updateText(id = "p1", newText = "updated")

        val updated = repo.listByNote("n1").single()
        assertEquals("updated", updated.text)

        // 5) delete
        repo.delete("p1")
        assertEquals(0, repo.listByNote("n1").size)
    }
}
