package apeiron.mobile



import android.content.Context
import androidx.room.Room
import apeiron.core.paragraph.ParagraphUseCase
import apeiron.infra.mobile.ParagraphRepositoryRoom
import apeiron.infra.mobile.db.ApeironDatabase

class AppContainer(context: Context) {
    private val db: ApeironDatabase =
        Room.databaseBuilder(
            context,
            ApeironDatabase::class.java,
            "apeiron.db")
            .build()

    val paragraphRepository = ParagraphRepositoryRoom(db.paragraphDao())

    val paragraphUseCase = ParagraphUseCase(paragraphRepository)
}