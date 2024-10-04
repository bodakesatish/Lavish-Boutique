package com.bodakesatish.templates.ui.services

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.templates.databinding.FragmentCreateOrEditServiceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCreateOrEditServices : Fragment() {

    private val tag = this.javaClass.simpleName

    private var _binding : FragmentCreateOrEditServiceBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CreateOrEditServicesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(tag , "In $tag onCreateView")
        _binding = FragmentCreateOrEditServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(tag , "In $tag onViewCreated")

        initView()
        initObserver()
        initListener()
    }

    private fun initView() {
        with(binding) {

        }
    }

    private fun initObserver() {
        viewModel.addServiceResponse.observe(viewLifecycleOwner) {
            Log.i(tag , "In $tag initObserver addServiceResponse")
            Toast.makeText(requireContext(), "Service Created", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            val service = Service(
                id = 0,
                serviceName = binding.evServiceName.editText?.text.toString(),
                serviceDescription = binding.evServiceDescription.editText?.text.toString(),
                servicePrice = binding.evServicePrice.editText?.text.toString().toInt()
            )
            viewModel.createOrEditService(service)
        }
    }

}