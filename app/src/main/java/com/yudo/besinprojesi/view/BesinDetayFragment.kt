package com.yudo.besinprojesi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yudo.besinprojesi.databinding.FragmentBesinDetayBinding
import com.yudo.besinprojesi.util.gorselIndir
import com.yudo.besinprojesi.util.placeHolderYap
import com.yudo.besinprojesi.viewmodel.BesinDetayiViewModel


class BesinDetayFragment : Fragment() {
    private var _binding: FragmentBesinDetayBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel : BesinDetayiViewModel
    var besinId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBesinDetayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[BesinDetayiViewModel::class.java]

        arguments?.let {
            besinId = BesinDetayFragmentArgs.fromBundle(it).besinID

        }

        viewModel.roomVerisiniAl(besinId)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.besinLiveData.observe(viewLifecycleOwner){
            binding.besinIsim.text = it.besinIsim
            binding.besinKalori.text = it.besinKalori
            binding.besinYag.text = it.besinYag
            binding.besinProtein.text = it.besinProtein
            binding.besinKarbonhidrat.text = it.besinKarbonhidrat
            binding.besinImage.gorselIndir(it.besinGorsel, placeHolderYap(requireContext()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}