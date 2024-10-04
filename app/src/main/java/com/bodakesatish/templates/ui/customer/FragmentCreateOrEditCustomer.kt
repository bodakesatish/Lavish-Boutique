package com.bodakesatish.templates.ui.customer

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.templates.R
import com.bodakesatish.templates.databinding.FragmentCreateOrEditCustomerBinding
import com.bodakesatish.templates.databinding.FragmentCreateOrEditServiceBinding
import com.bodakesatish.templates.util.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCreateOrEditCustomer : Fragment() {

    private val tag = this.javaClass.simpleName

    private var _binding : FragmentCreateOrEditCustomerBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CreateOrEditCustomerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(tag , "In $tag onCreateView")
        _binding = FragmentCreateOrEditCustomerBinding.inflate(inflater, container, false)
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
            evCustomerName.hint = Html.fromHtml(resources.getString(R.string.label_full_name))
            evCustomerName.editText?.showKeyboard()
        }
    }

    private fun initObserver() {
        viewModel.addCustomerResponse.observe(viewLifecycleOwner) {
            Log.i(tag , "In $tag initObserver addCustomerResponse")
            Toast.makeText(requireContext(), "Customer Created", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun validateInput(): Boolean {
        if(binding.evCustomerName.editText?.text.toString().isBlank()) {
            binding.evCustomerName.error = "Customer Name is required"
          return false
        } else if(binding.evPhoneNumber.editText?.text.toString()
                .isNotEmpty() && binding.evPhoneNumber.editText?.text.toString().length != 10) {
            binding.evCustomerName.error = ""
            binding.evPhoneNumber.error = "Please enter valid mobile number"
            return false
        }
        return true
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            if(validateInput()) {
                val service = Customer(
                    id = 0,
                    customerName = binding.evCustomerName.editText?.text.toString(),
                    customerPhone = binding.evPhoneNumber.editText?.text.toString()
                )
                viewModel.createOrEditCustomer(service)
            }
        }
    }

}