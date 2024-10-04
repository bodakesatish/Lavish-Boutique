package com.bodakesatish.templates.ui.bill

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.templates.databinding.FragmentCreateOrEditBillBinding
import com.bodakesatish.templates.databinding.ItemLayoutBinding
import com.bodakesatish.templates.ui.services.adapter.ServicesAdapter
import com.bodakesatish.templates.util.AppArrayAdapter
import com.bodakesatish.templates.util.AppListPopupWindow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCreateOrEditBill : Fragment() {

    private val tag = this.javaClass.simpleName

    private var _binding: FragmentCreateOrEditBillBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateOrEditBillViewModel by viewModels()

    private val adapterScheme: ServicesAdapter = ServicesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(tag, "In $tag onCreateView")
        _binding = FragmentCreateOrEditBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(tag, "In $tag onViewCreated")

        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
        initObserver()
        initListener()

//        // In your activity or fragment
//        val onBackPressedCallback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // Handle the back press here
//                Toast.makeText(requireContext(), "onBackPressed", Toast.LENGTH_SHORT).show()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

    }



    private fun initView() {
        with(binding) {
            adapterScheme.forceCheckBox(true)
            rvServiceList.apply {
                setHasFixedSize(true)
                adapter = adapterScheme
            }
        }
    }

    private fun initObserver() {
        viewModel.addJobResponse.observe(viewLifecycleOwner) {
            Log.i(tag, "In $tag initObserver addJobResponse")
            Toast.makeText(requireContext(), "Job Created", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        viewModel.serviceListResponse.observe(viewLifecycleOwner) { list ->
            adapterScheme.submitList(list)
        }
    }

    private fun initListener() {
        binding.evCustomerList.editText?.setOnClickListener {
            showCustomerList(it)
        }
        adapterScheme.setOnClickListener {
            setServicesCountAndTotalBill(adapterScheme.getSelectedSchemeList())
        }
        adapterScheme.setOnLongClickListener {
            setServicesCountAndTotalBill(adapterScheme.getSelectedSchemeList())
        }
        binding.btnAdd.setOnClickListener {
            viewModel.createOrEditJob(adapterScheme.getSelectedSchemeList())
        }
        binding.cardServices.setOnClickListener {
            binding.rvServiceList.let {
                if (it.visibility == View.GONE) {
                    it.visibility = View.VISIBLE
                    it.animate().alpha(1f).setDuration(500).start()
                    val rotateAnimation = RotateAnimation(
                        0f, 180F,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                    )
                    rotateAnimation.duration = 500
                    rotateAnimation.fillAfter = true // Keep the final rotation after animation
                    binding.ivToggle.startAnimation(rotateAnimation)
                } else {
                    it.visibility = View.GONE
                    it.animate().alpha(0f).setDuration(500).start()
                    val rotateAnimation = RotateAnimation(
                        180f, 0F,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                    )
                    rotateAnimation.duration = 500
                    rotateAnimation.fillAfter = true // Keep the final rotation after animation
                    binding.ivToggle.startAnimation(rotateAnimation)
                }
            }
        }

        binding.cardPayment.setOnClickListener {
            binding.cvJobBill.let {
                if (it.visibility == View.GONE) {
                    it.visibility = View.VISIBLE
                    it.animate().alpha(1f).setDuration(500).start()
                    val rotateAnimation = RotateAnimation(
                        0f, 180F,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                    )
                    rotateAnimation.duration = 500
                    rotateAnimation.fillAfter = true // Keep the final rotation after animation
                    binding.ivPaymentToggle.startAnimation(rotateAnimation)
                } else {
                    it.visibility = View.GONE
                    it.animate().alpha(0f).setDuration(500).start()
                    val rotateAnimation = RotateAnimation(
                        180f, 0F,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                    )
                    rotateAnimation.duration = 500
                    rotateAnimation.fillAfter = true // Keep the final rotation after animation
                    binding.ivPaymentToggle.startAnimation(rotateAnimation)
                }
            }
        }


    }

    private fun setServicesCountAndTotalBill(selectedSchemeList: List<Service>) {
        binding.apply {
            tvTotalServicesValue.text = "${selectedSchemeList.size}"
            val totalBill = selectedSchemeList.sumOf {
                it.servicePrice
            }
            viewModel.setTotalBill(totalBill)
            tvTotalBillValue.text = "$totalBill Rs"
       }
    }

    private fun showCustomerList(anchorView: View) {
        val appArrayAdapter = AppArrayAdapter(
            requireContext(),
            viewModel.getCustomerListModel()
        ) { listBinding: ViewBinding, course: Customer ->
            (listBinding as ItemLayoutBinding).itemNameTextView.text = course.customerName
        }
        AppListPopupWindow.showListPopupWindow(
            anchorView,
            appArrayAdapter
        ) { position ->
            viewModel.setCustomer(viewModel.getCustomerListModel()[position])
            binding.evCustomerList.editText?.setText(viewModel.getCustomerListModel()[position].customerName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}