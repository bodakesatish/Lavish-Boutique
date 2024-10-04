package com.bodakesatish.templates.ui.bill.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.data.common.DateHelper
import com.bodakesatish.domain.model.response.Bill
import com.bodakesatish.templates.databinding.ListRowBillBinding

class BillAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tag = this.javaClass.simpleName
    var onSchemeSelected: ((scheme: Bill) -> Unit)? = null
    var isCheckBoxEnabled = false

    private val selectedSchemeList = mutableListOf<Bill>()

    private val diffUtil = object : DiffUtil.ItemCallback<Bill>() {

        override fun areItemsTheSame(oldItem: Bill, newItem: Bill): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bill, newItem: Bill): Boolean {
            return oldItem == newItem
        }

    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListRowBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    fun submitList(list: List<Bill>) {
        asyncListDiffer.submitList(list)
    }

    fun getSelectedSchemeList(): List<Bill> {
        return selectedSchemeList
    }

    fun clearSelectedSchemeList() {
        selectedSchemeList.clear()
    }

    fun onClickSchemeItem(selectedScheme: Bill, position: Int) {
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

    fun setOnClickListener(onSchemeSelected: (Bill) -> Unit) {
        this.onSchemeSelected = onSchemeSelected
    }

    fun setOnLongClickListener(onSchemeSelected: (Bill) -> Unit) {
        this.onSchemeSelected = onSchemeSelected
    }

    inner class SchemeViewHolder(private val binding: ListRowBillBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schemeModel: Bill) {

            with(binding) {

//                tvSchemeCode.text = schemeModel.jobTotalPrice.toString()
                header.text = schemeModel.customerName.get(0).toString()
                customerNameView.text = schemeModel.customerName
                reviewTextView.text = schemeModel.serviceList
                dateTextView.text = DateHelper.getFormattedDate(schemeModel.jobDate, DateHelper.DATE_FORMAT_MMM_dd_yyyy)
                ratingView.text = DateHelper.formatTime(schemeModel.jobDate)
                totalPrice.text = schemeModel.jobTotalPrice.toString()+" ₹"

//                cbScheme.visibility = if (isCheckBoxEnabled) View.VISIBLE else View.GONE

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

//                cbScheme.setOnClickListener {
//                    onClickSchemeItem(selectedScheme = schemeModel, position = adapterPosition)
//                }

            }

        }
    }
}