package com.bodakesatish.templates.ui.customer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.templates.databinding.ListRowCustomerBinding

class CustomerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tag = this.javaClass.simpleName
    var onSchemeSelected: ((scheme: Customer) -> Unit)? = null
    var isCheckBoxEnabled = false

    private val selectedSchemeList = mutableListOf<Customer>()

    private val diffUtil = object : DiffUtil.ItemCallback<Customer>() {

        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem == newItem
        }

    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListRowCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    fun submitList(list: List<Customer>) {
        asyncListDiffer.submitList(list)
    }

    fun getSelectedSchemeList(): List<Customer> {
        return selectedSchemeList
    }

    fun clearSelectedSchemeList() {
        selectedSchemeList.clear()
    }

    fun onClickSchemeItem(selectedScheme: Customer, position: Int) {
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

    fun setOnClickListener(onSchemeSelected: (Customer) -> Unit) {
        this.onSchemeSelected = onSchemeSelected
    }

    fun setOnLongClickListener(onSchemeSelected: (Customer) -> Unit) {
        this.onSchemeSelected = onSchemeSelected
    }

    inner class SchemeViewHolder(private val binding: ListRowCustomerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schemeModel: Customer) {

            with(binding) {

                tvSchemeCode.text = schemeModel.customerPhone
                tvSchemeName.text = (adapterPosition+1).toString() + ". " + schemeModel.customerName
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