package bangkit.android.capstone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class YesAdapter(private val listKulit: ArrayList<PilihKulit>) :
    RecyclerView.Adapter<YesAdapter.ViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvName: TextView = itemView.findViewById(R.id.titlecard)
        var imgPhoto: ImageView = itemView.findViewById(R.id.imagecard)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_horizontal, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = listKulit[position]

        Glide.with(holder.itemView.context)
            .load(cat.foto)
            .apply(RequestOptions().override(100, 100))
            .into(holder.imgPhoto)

        holder.tvName.text = cat.title

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listKulit[position], position) }
    }

    override fun getItemCount(): Int {
        return listKulit.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PilihKulit, position: Int)
    }


}