package com.route.moviesapp_task.ui.fragments.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.domain.models.toprated.TopRated
import com.route.moviesapp_task.databinding.ItemTopratedBinding

class TopRatedAdapter
    (private var listOfTopRated: List<TopRated>) : Adapter<TopRatedAdapter.MyViewHolder>() {
    var myDiffUtil: MyDiffUtil? = null

    class MyViewHolder(val binding: ItemTopratedBinding) : ViewHolder(binding.root) {
        fun bind(topRated: TopRated) {
            binding.topRated = topRated
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemTopratedBinding =
            ItemTopratedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfTopRated.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val topRated = listOfTopRated[position]
        holder.bind(topRated)
    }

    fun updateList(newListOfTopRated: List<TopRated>) {
        myDiffUtil = MyDiffUtil(listOfTopRated, newListOfTopRated)
        val diffResult = DiffUtil.calculateDiff(myDiffUtil!!)
        listOfTopRated = newListOfTopRated
        diffResult.dispatchUpdatesTo(this)
    }

    class MyDiffUtil(
        private val oldList: List<TopRated>,
        private val newList: List<TopRated>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}