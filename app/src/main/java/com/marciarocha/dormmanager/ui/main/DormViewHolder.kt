package com.marciarocha.dormmanager.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.marciarocha.dormmanager.domain.model.Dorm
import kotlinx.android.synthetic.main.dorm_item.view.*

class DormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(dorm: Dorm, onSelect: (Dorm) -> Unit) {
        itemView.dorm_description.text = dorm.description
        itemView.price_per_bed.text = "$${dorm.getFormattedPrice()} "
        itemView.book_button.setOnClickListener { onSelect(dorm) }
    }
}