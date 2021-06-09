package bangkit.android.capstone.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bangkit.android.capstone.data.ProdukDBEntity

@Dao
interface ProdukDao {

    @Query("SELECT * FROM produk_entity WHERE category=:category AND tipe_kulit=:type")
    fun getProdukByCategoryAndType(category: Int, type: Int): LiveData<List<ProdukDBEntity>>

    @Query("SELECT * FROM produk_entity WHERE id=(SELECT id FROM produk_entity WHERE nama=:nama )")
    fun getProdukByName(nama: String): LiveData<ProdukDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduk(produks: ArrayList<ProdukDBEntity>)

}