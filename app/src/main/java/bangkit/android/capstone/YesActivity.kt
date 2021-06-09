package bangkit.android.capstone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bangkit.android.capstone.ui.detaill.ProductActivity

class YesActivity : AppCompatActivity() {


    private lateinit var rvkulit: RecyclerView
    private var list: ArrayList<PilihKulit> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yes)

        rvkulit = findViewById(R.id.listhorizontal)
        rvkulit.setHasFixedSize(true)

        list.addAll(PilihKulitData.listKulit)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvkulit.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val kulitAdapter = YesAdapter(list)
        rvkulit.adapter = kulitAdapter

        kulitAdapter.setOnItemClickCallback(object : YesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PilihKulit, position: Int) {
                val moves = Intent(this@YesActivity, ProductActivity::class.java).apply {
                    putExtra(ProductActivity.EXTRA_STRING, data.title)
                    putExtra(ProductActivity.EXTRA_TIPE_KULIT, position)
                }
                startActivity(moves)

            }
        })
    }
}

/*private lateinit var adapter: YesAdapter
private lateinit var dataTitle: Array<String>
private lateinit var dataFoto: TypedArray

private var kulite = arrayListOf<PilihKulit>()

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_yes)

    val listCard: ListView = findViewById(R.id.listhorizontal)
    adapter = YesAdapter(this)
    listCard.adapter = adapter

    prepare()
    addItem()



}

private fun prepare() {
    dataTitle = resources.getStringArray(R.array.data_kulit)
    dataFoto = resources.obtainTypedArray(R.array.data_foto)
}

private fun addItem() {
    for (position in dataTitle.indices) {
        val user = PilihKulit(
            dataFoto.getResourceId(position, -1),
            dataTitle[position]

        )
        kulite.add(user)
    }
    adapter.kulite = kulite
}*/



