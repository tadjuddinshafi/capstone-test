package bangkit.android.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import bangkit.android.capstone.databinding.ActivityDetailProdukBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailProdukActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAMA = "extra_nama"

    }


    private lateinit var binding: ActivityDetailProdukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productName = intent.getStringExtra(EXTRA_NAMA)


        val produkRepository = ProdukRepository(this).getProdukByName(
            productName ?: "Wardah Aloe Hydramild Facial Wash"
        ).observe(this, {
            if(it != null){
                with(binding){
                    val image = ContextCompat.getDrawable(
                        this@DetailProdukActivity,
                        resources.getIdentifier(
                            it.poster,
                            "drawable",
                            packageName
                        )
                    )
                    Glide.with(this@DetailProdukActivity).load(image).apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_baseline_broken_image_24))
                        .centerCrop().into(ivPosterDetailProduct)
                    tvCompositionDetailProduct.text = it.komposisi
                    tvNameDetailProduct.text = it.produk
                }
            }
        })

    }
}