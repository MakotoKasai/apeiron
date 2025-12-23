package apeiron.infra.mobile.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ParagraphDaoRobolectricTest {

    private lateinit var db: ApeironDatabase
    private lateinit var dao: ParagraphDao

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, ApeironDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.paragraphDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insert_and_listUnassigned() = runBlocking {
        val now = System.currentTimeMillis()

        dao.insert(
            ParagraphEntity(
                id = "p1",
                noteId = null,
                text = "hello",
                createdAtMillis = now,
                updatedAtMillis = now
            )
        )

        val list = dao.listUnassigned()
        assertEquals(1, list.size)
    }

    @Test
    fun move_update_delete_flow() = runBlocking {
        val t0 = System.currentTimeMillis()

        // 1) insert (unassigned)
        dao.insert(
            ParagraphEntity(
                id = "p1",
                noteId = null,
                text = "hello",
                createdAtMillis = t0,
                updatedAtMillis = t0
            )
        )
        assertEquals(1, dao.listUnassigned().size)

        // 2) moveToNote
        val moved = dao.moveToNote(id = "p1", noteId = "n1", updatedAtMillis = t0 + 1)
        assertEquals(1, moved) // 更新行数
        assertEquals(0, dao.listUnassigned().size)
        assertEquals(1, dao.listByNote("n1").size)

        // 3) updateText
        val updated = dao.updateText(id = "p1", newText = "updated", updatedAtMillis = t0 + 2)
        assertEquals(1, updated)

        val afterUpdate = dao.listByNote("n1").single()
        assertEquals("updated", afterUpdate.text)
        assertEquals(t0 + 2, afterUpdate.updatedAtMillis)

        // 4) delete
        val deleted = dao.delete("p1")
        assertEquals(1, deleted)
        assertEquals(0, dao.listByNote("n1").size)
    }
}