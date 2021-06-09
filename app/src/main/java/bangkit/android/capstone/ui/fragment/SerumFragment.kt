package bangkit.android.capstone.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.android.capstone.DetailProdukActivity
import bangkit.android.capstone.ProdukRepository
import bangkit.android.capstone.ViewModelFactory
import bangkit.android.capstone.databinding.FragmentSerumBinding
import bangkit.android.capstone.ui.adapter.ProdukAdapter

class SerumFragment : Fragment() {

    companion object{
        const val EXTRA_SERUM = "extra_serum"
    }

    private lateinit var fragmentSerumBinding: FragmentSerumBinding
    private var jenisKulit = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSerumBinding = FragmentSerumBinding.inflate(layoutInflater, container,false)
        return fragmentSerumBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(ProdukRepository(requireContext()))
        jenisKulit = arguments?.getInt(EXTRA_SERUM) ?: 0

        if (activity != null){
            val viewModel = ViewModelProvider(this, factory)[SerumViewModel::class.java]

            val serumAdapter = ProdukAdapter().apply {
                setOnItemClickCallback(object : ProdukAdapter.OnItemClickCallback{
                    override fun onItemClicked(nama: String) {
                        startActivity(Intent(requireContext(), DetailProdukActivity::class.java).apply {
                            putExtra(DetailProdukActivity.EXTRA_NAMA, nama)
                        })
                    }

                })
            }

            viewModel.getSerum(2, jenisKulit).observe(viewLifecycleOwner, {
                serumAdapter.setItems(it)
                serumAdapter.notifyDataSetChanged()
            })

            with(fragmentSerumBinding.rvSerum){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = serumAdapter
            }
        }
    }
}
