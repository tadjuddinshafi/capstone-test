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
import bangkit.android.capstone.databinding.FragmentFacewashBinding
import bangkit.android.capstone.ui.adapter.ProdukAdapter


class FacewashFragment : Fragment() {

    companion object{
        const val EXTRA_FACEWASH = "extra_facewash"
    }

    private lateinit var fragmentFacewashBinding: FragmentFacewashBinding
    private var jenisKulit = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentFacewashBinding = FragmentFacewashBinding.inflate(layoutInflater, container, false)
        return fragmentFacewashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(ProdukRepository((requireContext())))
        jenisKulit = arguments?.getInt(EXTRA_FACEWASH)?: 0

        if (activity != null) {
            val viewModel = ViewModelProvider(this, factory)[FacewashViewModel::class.java]

            val facewashAdapter = ProdukAdapter().apply {
                setOnItemClickCallback(object : ProdukAdapter.OnItemClickCallback{
                    override fun onItemClicked(nama: String) {
                        startActivity(Intent(requireContext(), DetailProdukActivity::class.java).apply {
                            putExtra(DetailProdukActivity.EXTRA_NAMA, nama)
                        })
                    }

                })
            }

            viewModel.getFaceWash(0, jenisKulit).observe(viewLifecycleOwner, {
                facewashAdapter.setItems(it)
                facewashAdapter.notifyDataSetChanged()
            })
            with(fragmentFacewashBinding.rvFacewash) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = facewashAdapter
            }
        }
    }
}