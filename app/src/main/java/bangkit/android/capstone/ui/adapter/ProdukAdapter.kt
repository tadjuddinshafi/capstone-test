package bangkit.android.capstone.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import bangkit.android.capstone.R
import bangkit.android.capstone.data.ProdukEntity
import bangkit.android.capstone.databinding.ItemProductBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProdukAdapter : RecyclerView.Adapter<ProdukAdapter.ListViewHolder>() {

    private var listDatas = ArrayList<ProdukEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setItems(data: List<ProdukEntity>) {
        if (data == null) return
        this.listDatas.clear()
        this.listDatas.addAll(data)
    }

    class ListViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ProdukEntity) {
            with(binding) {
                tvNamaproduk.text = data.produk
                tvKomposisi.text = data.komposisi
                val image = ContextCompat.getDrawable(
                    itemView.context,
                    itemView.context.resources.getIdentifier(
                        data.poster,
                        "drawable",
                        itemView.context.packageName
                    )
                )
                Glide.with(itemView.context).load(image).apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_baseline_broken_image_24)
                )
                    .centerCrop().into(imgItems)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemProductBinding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemProductBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listDatas[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDatas[position].produk)
        }
    }

    override fun getItemCount(): Int = listDatas.size

    interface OnItemClickCallback {
        fun onItemClicked(nama: String)
    }
}