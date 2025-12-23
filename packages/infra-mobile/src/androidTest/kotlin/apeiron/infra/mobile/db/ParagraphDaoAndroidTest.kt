package apeiron.infra.mobile.db


import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ParagraphDaoAndroidTest {

    private lateinit var db: ApeironDatabase
    private lateinit var dao: ParagraphDao

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, ApeironDatabase::class.java)
            .allowMainThreadQueries() // テストなのでOK
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
        assertEquals("p1", list[0].id)
        assertEquals("hello", list[0].text)
        assertEquals(null, list[0].noteId)
    }
}