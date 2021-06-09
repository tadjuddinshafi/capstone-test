package bangkit.android.capstone.ui.detaill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import bangkit.android.capstone.R
import bangkit.android.capstone.data.ProdukEntity
import bangkit.android.capstone.databinding.ActivityProductBinding
import bangkit.android.capstone.ui.adapter.PagerAdapter

class ProductActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_STRING = "extra_jeniskulit"
        const val EXTRA_TIPE_KULIT = "extra_tipe_kulit"

    }

    private var jenisKulit = ""
    private var tipeKulit = 0


    private lateinit var activityProductBinding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityProductBinding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(activityProductBinding.root)

        jenisKulit = intent.getStringExtra(EXTRA_STRING)?: "null"
        tipeKulit = intent.getIntExtra(EXTRA_TIPE_KULIT, 0)


        val pagerAdapter = PagerAdapter(this, supportFragmentManager, tipeKulit)
        activityProductBinding.viewpager.adapter = pagerAdapter
        activityProductBinding.tablayout.setupWithViewPager(activityProductBinding.viewpager)
        supportActionBar?.elevation = 0f

        activityProductBinding.jenisKulitProduct.text = jenisKulit
    }
}