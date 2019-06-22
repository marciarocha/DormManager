package com.marciarocha.dormmanager.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marciarocha.dormmanager.R
import com.marciarocha.dormmanager.domain.model.Dorm

class DormListAdapter(private val onSelect: (Dorm) -> Unit) : RecyclerView.Adapter<DormViewHolder>() {

    private var data: MutableList<Dorm> = mutableListOf()

    fun update(data: MutableList<Dorm>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun clear() = data.clear()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DormViewHolder {
        return DormViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dorm_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DormViewHolder, position: Int) {
        holder.bind(data[position], onSelect)
    }
}