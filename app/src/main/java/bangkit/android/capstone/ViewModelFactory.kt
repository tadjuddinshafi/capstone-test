package bangkit.android.capstone

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bangkit.android.capstone.ui.fragment.FacewashViewModel
import bangkit.android.capstone.ui.fragment.SerumViewModel
import bangkit.android.capstone.ui.fragment.TonerViewModel

class ViewModelFactory private constructor(private val produkRepository: ProdukRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(produkRepository: ProdukRepository): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(produkRepository)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FacewashViewModel::class.java)) {
            return FacewashViewModel(produkRepository) as T
        } else if (modelClass.isAssignableFrom(TonerViewModel::class.java)) {
            return TonerViewModel(produkRepository) as T
        } else if (modelClass.isAssignableFrom(SerumViewModel::class.java)) {
            return SerumViewModel(produkRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}