package bangkit.android.capstone

object PilihKulitData {

    private val kulitTitle = arrayOf("Kulit Kering",
            "Kulit Minyak",
            "Kulit Kombinasi",
            "Kulit Sensitif",
            "Kulit Normal")

    private val kulitFoto = intArrayOf(R.drawable.icon_kering,
    R.drawable.icon_berminyak,
    R.drawable.icon_kombinasi,
    R.drawable.icon_sensitif,
    R.drawable.icon_normal)

    val listKulit: ArrayList<PilihKulit>
    get() {
        val list = arrayListOf<PilihKulit>()
        for (position in kulitTitle.indices){
            val kulit = PilihKulit()
            kulit.title = kulitTitle[position]
            kulit.foto = kulitFoto[position]
            list.add(kulit)
        }
        return list
    }
}