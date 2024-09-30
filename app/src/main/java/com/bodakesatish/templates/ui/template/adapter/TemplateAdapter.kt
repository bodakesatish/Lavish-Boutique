package com.bodakesatish.templates.ui.template.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.domain.model.response.Template
import com.bodakesatish.templates.databinding.ListRowTemplateBinding

class TemplateAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tag = this.javaClass.simpleName
    var onSchemeSelected: ((scheme: Template) -> Unit)? = null
    var isCheckBoxEnabled = false

    private val selectedSchemeList = mutableListOf<Template>()

    private val diffUtil = object : DiffUtil.ItemCallback<Template>() {

        override fun areItemsTheSame(oldItem: Template, newItem: Template): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Template, newItem: Template): Boolean {
            return oldItem == newItem
        }

    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListRowTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SchemeViewHolder) {
            holder.bind(asyncListDiffer.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    fun submitList(list: List<Template>) {
        val combinedList = asyncListDiffer.currentList + list

        asyncListDiffer.submitList(combinedList)
    }

    fun getSelectedSchemeList(): List<Template> {
        return selectedSchemeList
    }

    fun clearSelectedSchemeList() {
        selectedSchemeList.clear()
    }

    fun onClickSchemeItem(selectedScheme: Template, position: Int) {
        if (selectedSchemeList.contains(selectedScheme)) {
            selectedSchemeList.remove(selectedScheme)
        } else {
            selectedSchemeList.add(selectedScheme)
        }
        if (selectedSchemeList.isEmpty()) {
            isCheckBoxEnabled = false
            removeSelection()
        } else {
            isCheckBoxEnabled = true
            notifyItemRangeChanged(0, asyncListDiffer.currentList.size)
        }
    }

    fun removeSelection() {
        selectedSchemeList.clear()
        isCheckBoxEnabled = false
        notifyItemRangeChanged(0, asyncListDiffer.currentList.size)
    }

    fun setOnClickListener(onSchemeSelected: (Template) -> Unit) {
        this.onSchemeSelected = onSchemeSelected
    }

    fun setOnLongClickListener(onSchemeSelected: (Template) -> Unit) {
        this.onSchemeSelected = onSchemeSelected
    }

    inner class SchemeViewHolder(private val binding: ListRowTemplateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schemeModel: Template) {

            with(binding) {

                tvSchemeCode.text = schemeModel.schemeCode
                tvSchemeName.text = (adapterPosition+1).toString() + ". " + schemeModel.schemeName
                cbScheme.visibility = if (isCheckBoxEnabled) View.VISIBLE else View.GONE

                root.setOnClickListener {
                    if (isCheckBoxEnabled) {
                        onClickSchemeItem(selectedScheme = schemeModel, position = adapterPosition)
                    } else {
                        onSchemeSelected?.invoke(schemeModel)
                    }
                }

                root.setOnLongClickListener {
                    if (!isCheckBoxEnabled) {
                        onClickSchemeItem(selectedScheme = schemeModel, position = adapterPosition)
                    }
                    true
                }

                cbScheme.setOnClickListener {
                    onClickSchemeItem(selectedScheme = schemeModel, position = adapterPosition)
                }

            }

        }
    }
}