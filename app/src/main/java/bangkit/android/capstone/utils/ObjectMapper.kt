package bangkit.android.capstone.utils

import bangkit.android.capstone.data.ProdukDBEntity
import bangkit.android.capstone.data.ProdukEntity

object ObjectMapper {

    fun ProdukDBEntity.toProductEntity(): ProdukEntity = ProdukEntity(
        poster,
        produk,
        komposisi
    )

}