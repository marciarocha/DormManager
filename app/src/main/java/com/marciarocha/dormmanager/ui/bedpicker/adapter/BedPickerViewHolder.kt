package com.marciarocha.dormmanager.ui.bedpicker.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_of_beds_item.view.*

class BedPickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(number: Int, onSelect: (Int) -> Unit) {
        itemView.item_number.text = "$number"
        itemView.number_of_beds_layout.setOnClickListener { onSelect(number) }
    }
}