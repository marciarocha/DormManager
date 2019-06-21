package com.marciarocha.dormmanager.ui.bedpicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marciarocha.dormmanager.R

class BedPickerAdapter(
    private val onSelect: (Int) -> Unit,
    private val values: List<Int>
) : RecyclerView.Adapter<BedPickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BedPickerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.number_of_beds_item, parent, false)
        return BedPickerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BedPickerViewHolder, position: Int) {
        holder.bind(values[position], onSelect)
    }

    override fun getItemCount(): Int = values.size
}
