package bangkit.android.capstone

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import bangkit.android.capstone.data.ProdukDBEntity
import bangkit.android.capstone.data.ProdukEntity
import bangkit.android.capstone.room.ProdukDao
import bangkit.android.capstone.room.ProdukDatabase
import bangkit.android.capstone.utils.ObjectMapper.toProductEntity
import java.util.concurrent.Executors

class ProdukRepository(context: Context) {
    private val executorService = Executors.newSingleThreadExecutor()
    private var dao: ProdukDao

    init {
        val db = ProdukDatabase.getDatabase(context)
        dao = db.produkDao()
    }

    fun getProduk(category: Int, type: Int): LiveData<ArrayList<ProdukEntity>> =
            Transformations.map(dao.getProdukByCategoryAndType(category, type)){ oldList ->
                val list = ArrayList<ProdukEntity>()
                oldList.forEach {
                    list.add(it.toProductEntity())
                }
                list
            }

    fun insertProduk(listProduk: ArrayList<ProdukDBEntity>) =
        executorService.execute{
            dao.insertProduk(listProduk)
        }

    fun getProdukByName(name: String): LiveData<ProdukEntity> =
        Transformations.map(dao.getProdukByName(name)){ old ->
            old.toProductEntity()
        }

}