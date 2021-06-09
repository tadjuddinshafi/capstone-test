package bangkit.android.capstone.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produk_entity")
data class ProdukDBEntity(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "tipe_kulit")
    val tipeKulit: Int = 0,
    @ColumnInfo(name = "category")
    val category: Int = 0,
    @ColumnInfo(name = "img")
    val poster: String = "",
    @ColumnInfo(name = "nama")
    val produk: String = "",
    @ColumnInfo(name = "komposisi")
    val komposisi: String = ""
)
