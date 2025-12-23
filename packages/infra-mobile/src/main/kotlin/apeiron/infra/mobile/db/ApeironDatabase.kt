package apeiron.infra.mobile.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ParagraphEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApeironDatabase : RoomDatabase() {
    abstract fun paragraphDao(): ParagraphDao
}