package com.menna.moviesapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.menna.moviesapp.R
import com.menna.moviesapp.data_layer.entity.Category

class CategoryAdapter(
        private var categoryList: List<Category>,
        private var listener : OnClickCategoryListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text=categoryList[position].name
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            R.layout.item_categoory
            , parent, false)
        return ViewHolder(view)
    }
    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener{

        var title: TextView = view.findViewById(R.id.name)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            when(p0){

                itemView->{
                    listener.onCategoryClick(categoryList[adapterPosition])
                }

            }
        }

    }
    interface OnClickCategoryListener
    {
        fun onCategoryClick(item: Category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(list: List<Category>) {
        categoryList = list
        notifyDataSetChanged()

    }

}