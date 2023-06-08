package com.acilgin.chatgpt_recipe_finder.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acilgin.chatgpt_recipe_finder.R


class StepsRecyclerViewAdapter(private val steps: List<String>) :
    RecyclerView.Adapter<StepsRecyclerViewAdapter.StepsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.steps_item, parent, false)
        return StepsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.bind(steps[position])
    }

    override fun getItemCount(): Int = steps.size

    inner class StepsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stepTextView: TextView = itemView.findViewById(R.id.steps_label)

        fun bind(step: String) {
            stepTextView.text = step
        }
    }
}