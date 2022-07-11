package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTextBinding

class SplashAdapter : ListAdapter<String, SplashAdapter.SplashTextViewHolder>(SplashDiffUtil()) {

    inner class SplashTextViewHolder(val binding: ItemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            binding.itemText.text = text
        }
    }

    override fun submitList(list: List<String>?) {
        val result = arrayListOf<String>()
        list?.forEach { result.add(it) }
        super.submitList(result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplashTextViewHolder {
        val view = ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SplashTextViewHolder(view)
    }

    override fun onBindViewHolder(holder: SplashTextViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SplashDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}