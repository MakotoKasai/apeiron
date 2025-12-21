package apeiron.mobile



import android.content.Context
import androidx.room.Room
import apeiron.core.paragraph.ParagraphUseCase
import apeiron.infra.mobile.db.ApeironDatabase
import apeiron.infra.mobile.ParagraphRepositoryRoom

class AppContainer(context: Context) {
    private val db: ApeironDatabase =
        Room.databaseBuilder(context, ApeironDatabase::class.java, "apeiron.db")
            .fallbackToDestructiveMigration()
            .build()

    private val paragraphRepo = ParagraphRepositoryRoom(db.paragraphDao())

    val paragraphUseCase = ParagraphUseCase(paragraphRepo)
}