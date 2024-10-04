package com.bodakesatish.templates.ui.services.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.templates.databinding.ListRowTemplateBinding

class ServicesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tag = this.javaClass.simpleName
    var onSchemeSelected: ((scheme: Service) -> Unit)? = null
    var isCheckBoxEnabled = false
    var forceCheck = false

    private val selectedSchemeList = mutableListOf<Service>()

    private val diffUtil = object : DiffUtil.ItemCallback<Service>() {

        override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
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

    fun submitList(list: List<Service>) {
        asyncListDiffer.submitList(list)
    }

    fun getSelectedSchemeList(): List<Service> {
        return selectedSchemeList
    }

    fun clearSelectedSchemeList() {
        selectedSchemeList.clear()
    }

    fun onClickSchemeItem(selectedScheme: Service, position: Int) {
        if (selectedSchemeList.contains(selectedScheme)) {
            selectedSchemeList.remove(selectedScheme)
        } else {
            selectedSchemeList.add(selectedScheme)
        }
        if (selectedSchemeList.isEmpty()) {
            removeSelection()
            notifyItemChanged(position)
        } else {
            isCheckBoxEnabled = true
            notifyItemRangeChanged(0, asyncListDiffer.currentList.size)
        }
    }

    fun removeSelection() {
        if(!forceCheck) {
            selectedSchemeList.clear()
            isCheckBoxEnabled = false
            notifyItemRangeChanged(0, asyncListDiffer.currentList.size)
        }
    }

    fun setOnClickListener(onSchemeSelected: (Service) -> Unit) {
        this.onSchemeSelected = onSchemeSelected
    }

    fun setOnLongClickListener(onSchemeSelected: (Service) -> Unit) {
        this.onSchemeSelected = onSchemeSelected
    }

    fun toggleCheckBox(flag : Boolean) {
        isCheckBoxEnabled = flag
    }

    fun forceCheckBox(b: Boolean) {
        forceCheck = b
        isCheckBoxEnabled = b
    }

    inner class SchemeViewHolder(private val binding: ListRowTemplateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schemeModel: Service) {

            with(binding) {
                tvSchemeName.text = (adapterPosition+1).toString() + ". " + schemeModel.serviceName
                tvSchemeCode.text = schemeModel.serviceDescription
                cbScheme.visibility = if (isCheckBoxEnabled) View.VISIBLE else View.GONE
                if(isCheckBoxEnabled) {
                    cbScheme.isChecked = selectedSchemeList.contains(schemeModel)
                }
                root.setOnClickListener {
                    if (isCheckBoxEnabled) {
                        onClickSchemeItem(selectedScheme = schemeModel, position = adapterPosition)
                    }
                    onSchemeSelected?.invoke(schemeModel)
                }

                root.setOnLongClickListener {
                    if (!isCheckBoxEnabled) {
                        onClickSchemeItem(selectedScheme = schemeModel, position = adapterPosition)
                    }
                    onSchemeSelected?.invoke(schemeModel)
                    true
                }

                cbScheme.setOnClickListener {
                    onClickSchemeItem(selectedScheme = schemeModel, position = adapterPosition)
                }

            }

        }
    }
}