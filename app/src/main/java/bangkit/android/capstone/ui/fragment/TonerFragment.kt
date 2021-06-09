package bangkit.android.capstone.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.android.capstone.DetailProdukActivity
import bangkit.android.capstone.ProdukRepository
import bangkit.android.capstone.ViewModelFactory
import bangkit.android.capstone.databinding.FragmentTonerBinding
import bangkit.android.capstone.ui.adapter.ProdukAdapter

class TonerFragment : Fragment() {

    companion object{
        const val EXTRA_TONER = "extra_toner"
    }

    private lateinit var fragmentTonerBinding: FragmentTonerBinding
    private var jenisKulit = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTonerBinding = FragmentTonerBinding.inflate(layoutInflater, container, false)
        return fragmentTonerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(ProdukRepository(requireContext()))
        jenisKulit = arguments?.getInt(SerumFragment.EXTRA_SERUM) ?: 0

        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TonerViewModel::class.java]

            val tonerAdapter = ProdukAdapter().apply {
                setOnItemClickCallback(object : ProdukAdapter.OnItemClickCallback{
                    override fun onItemClicked(nama: String) {
                        startActivity(Intent(requireContext(), DetailProdukActivity::class.java).apply {
                            putExtra(DetailProdukActivity.EXTRA_NAMA, nama)
                        })
                    }

                })
            }

            viewModel.getToner(1, jenisKulit).observe(viewLifecycleOwner, {
                tonerAdapter.setItems(it)
                tonerAdapter.notifyDataSetChanged()
            })
            with(fragmentTonerBinding.rvToner) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tonerAdapter
            }
        }
    }
}