package com.route.moviesapp_task.ui.fragments.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.domain.models.popular.Popular
import com.route.domain.models.search.Search
import com.route.moviesapp_task.databinding.ItemMoviesBinding
import com.route.moviesapp_task.databinding.ItemSearchBinding

class SearchAdapter
    (private var listOfSearch: List<Search>) : Adapter<SearchAdapter.MyViewHolder>() {
    var myDiffUtil: MyDiffUtil? = null

    class MyViewHolder(val binding: ItemSearchBinding) : ViewHolder(binding.root) {
        fun bind(search: Search) {
            binding.search = search
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemSearchBinding =
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfSearch.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val search = listOfSearch[position]
        holder.bind(search)
    }

    fun updateList(newListOfSearch: List<Search>) {
        myDiffUtil = MyDiffUtil(listOfSearch, newListOfSearch)
        val diffResult = DiffUtil.calculateDiff(myDiffUtil!!)
        listOfSearch = newListOfSearch
        diffResult.dispatchUpdatesTo(this)
    }

    class MyDiffUtil(
        private val oldList: List<Search>,
        private val newList: List<Search>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}