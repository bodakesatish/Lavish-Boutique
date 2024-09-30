package com.bodakesatish.templates.ui.template

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bodakesatish.templates.databinding.FragmentTemplateListBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bodakesatish.templates.ui.template.adapter.TemplateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentTemplateList : Fragment() {

    private val tag = this.javaClass.simpleName

    private var _binding : FragmentTemplateListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : TemplateListViewModel by viewModels()

    private val adapterScheme : TemplateAdapter = TemplateAdapter()

    private var isLoadingEmails = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(tag , "In $tag onCreateView")
        _binding = FragmentTemplateListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(tag , "In $tag onViewCreated")

        initView()
        initObserver()
        initListener()
        fetchSchemeList()
    }

    private fun fetchSchemeList() {
        Log.i(tag , "In $tag fetchSchemeList")
        binding.postsProgressBar.visibility = View.VISIBLE
        viewModel.getTemplateListFlow("sdada")
    }

    private fun initView() {
        with(binding) {
            rvSchemeList.apply {
                setHasFixedSize(true)
                adapter = adapterScheme
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.pokemonDetail.collectLatest {
                binding.postsProgressBar.visibility = View.GONE
                it?.let { it1 -> adapterScheme.submitList(it1) }
            }
            viewModel.errorMessage.collectLatest {
                binding.postsProgressBar.visibility = View.GONE
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
            viewModel.isLoading.collectLatest {
                binding.postsProgressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Loading $it", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initListener() {
        binding.etSearchScheme.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.getTemplateList(binding.etSearchScheme.editText?.text.toString())
                Toast.makeText(requireContext(), binding.etSearchScheme.editText?.text.toString(), Toast.LENGTH_SHORT).show()

            }
            true
        }
        adapterScheme.setOnClickListener {

        }
        adapterScheme.setOnLongClickListener {

        }
        addScrollListener()
    }

    private fun addScrollListener() {


    }

}