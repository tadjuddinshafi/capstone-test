package bangkit.android.capstone.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import bangkit.android.capstone.ProdukRepository
import bangkit.android.capstone.data.ProdukEntity

class TonerViewModel(private val produkRepository: ProdukRepository) : ViewModel(){
    fun getToner(category: Int, type: Int): LiveData<ArrayList<ProdukEntity>> =
        produkRepository.getProduk(category, type)
}