package com.route.moviesapp_task.ui.fragments.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.domain.models.popular.Popular
import com.route.moviesapp_task.databinding.ItemMoviesBinding

class PopularAdapter
    (private var listOfPopular: List<Popular>, var onMoviesClick: (popular:Popular, position:Int) -> Unit) :
    Adapter<PopularAdapter.MyViewHolder>() {
    var myDiffUtil: MyDiffUtil? = null

    inner class MyViewHolder(val binding: ItemMoviesBinding) : ViewHolder(binding.root) {
        fun bind(popular: Popular,position:Int) {
            binding.popular = popular
            binding.main.setOnClickListener {
                onMoviesClick.invoke(popular,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemMoviesBinding =
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfPopular.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val popular = listOfPopular[position]
        holder.bind(popular,position)
    }

    fun updateList(newListOfPopular: List<Popular>) {
        myDiffUtil = MyDiffUtil(listOfPopular, newListOfPopular)
        val diffResult = DiffUtil.calculateDiff(myDiffUtil!!)
        listOfPopular = newListOfPopular
        diffResult.dispatchUpdatesTo(this)
    }

    class MyDiffUtil(
        private val oldList: List<Popular>,
        private val newList: List<Popular>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}