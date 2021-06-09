package bangkit.android.capstone.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bangkit.android.capstone.data.ProdukDBEntity

@Database(entities = [ProdukDBEntity::class], version = 1)
abstract class ProdukDatabase : RoomDatabase() {
    abstract fun produkDao(): ProdukDao

    companion object {

        @Volatile
        private var INSTANCE: ProdukDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ProdukDatabase {
            if (INSTANCE == null) {
                synchronized(ProdukDatabase::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            ProdukDatabase::class.java,
                            "produk_database.db"
                        ).setJournalMode(JournalMode.TRUNCATE).createFromAsset("database/produk_database.db").build()
                }
            }

            return INSTANCE as ProdukDatabase
        }
    }
}