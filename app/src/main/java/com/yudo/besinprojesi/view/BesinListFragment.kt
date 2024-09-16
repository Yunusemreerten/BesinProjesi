package com.yudo.besinprojesi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudo.besinprojesi.adapter.BesinRecyclerAdapter
import com.yudo.besinprojesi.databinding.FragmentBesinListBinding
import com.yudo.besinprojesi.service.BesinAPI
import com.yudo.besinprojesi.viewmodel.BesinListesiViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class BesinListFragment : Fragment() {

    // https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    private var _binding: FragmentBesinListBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel : BesinListesiViewModel
    private val besinRecyclerAdapter = BesinRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBesinListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this)[BesinListesiViewModel::class.java]

        binding.besinRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.besinRecyclerView.adapter = besinRecyclerAdapter


        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.besinRecyclerView.visibility = View.GONE
            binding.besinHataMesaji.visibility = View.GONE
            binding.besinYukleniyor.visibility = View.VISIBLE
            viewModel.refreshDataFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false

        }

        observeLiveData()

    }
    private fun observeLiveData(){
        viewModel.besinler.observe(viewLifecycleOwner){
            //adapter
            besinRecyclerAdapter.besinListesiniGuncelle(it)
            binding.besinRecyclerView.visibility = View.VISIBLE
        }

        viewModel.besinHataMesaji.observe(viewLifecycleOwner){
            if(it){
                binding.besinHataMesaji.visibility = View.VISIBLE
                binding.besinRecyclerView.visibility = View.GONE
            }else{
                binding.besinHataMesaji.visibility = View.GONE
            }

        }

        viewModel.besinYukleniyor.observe(viewLifecycleOwner){
            if(it){
                binding.besinHataMesaji.visibility = View.GONE
                binding.besinRecyclerView.visibility = View.GONE
                binding.besinYukleniyor.visibility = View.VISIBLE
            }else{
                binding.besinYukleniyor.visibility = View.GONE
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}