package com.bodakesatish.templates.ui.bill

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bodakesatish.templates.R
import com.bodakesatish.templates.databinding.FragmentBillListBinding
import com.bodakesatish.templates.ui.bill.adapter.BillAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentBillList : Fragment() {

    private val tag = this.javaClass.simpleName

    private var _binding: FragmentBillListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BillListViewModel by viewModels()

    private val adapterScheme: BillAdapter = BillAdapter()

    private var isLoadingEmails = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(tag, "In $tag onCreateView")
        _binding = FragmentBillListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(tag, "In $tag onViewCreated")

        initView()
        initObserver()
        initListener()
        fetchSchemeList()
    }

    private fun fetchSchemeList() {
        Log.i(tag, "In $tag fetchSchemeList")
        viewModel.getJobList()

    }

    private fun initView() {
        with(binding) {
            rvSchemeList.apply {
                setHasFixedSize(true)
                adapter = adapterScheme
//                addItemDecoration(
//                    DividerItemDecoration(
//                        requireContext(),
//                        DividerItemDecoration.VERTICAL
//                    )
//                )
            }

        }
    }

    private fun initObserver() {
        viewModel.customerListResponse.observe(viewLifecycleOwner) { list ->
            adapterScheme.submitList(list)
        }
    }

    private fun initListener() {

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.dest_create_job)
        }

        adapterScheme.setOnClickListener {

        }
        adapterScheme.setOnLongClickListener {

        }
        addScrollListener()
    }

    private fun addScrollListener() {


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}