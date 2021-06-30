package com.example.market.adapters.viewHolders

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.market.R
import com.example.market.adapters.CategoryRecyclerAdapter

class CategoryViewHolder(view:View , listener : CategoryRecyclerAdapter.onCategoryClickListener) : RecyclerView.ViewHolder(view) {

    val categoryTitle:TextView
    val categoryButton : FrameLayout
    val categoryBackground : FrameLayout
    init {
        categoryTitle = view.findViewById(R.id.categoryTitle)
        categoryButton = view.findViewById(R.id.categoryButton)
        categoryBackground = view.findViewById(R.id.categoryBackground)

        categoryButton.setOnClickListener{
           listener.onCategoryClick(adapterPosition)
        }
    }

}