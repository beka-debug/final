package com.example.market.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.market.R
import com.example.market.adapters.viewHolders.CategoryViewHolder
import com.example.market.models.Category

 class  CategoryRecyclerAdapter (private val categoryes: List<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {

     private lateinit var categoryClick : onCategoryClickListener

     interface onCategoryClickListener{
         fun onCategoryClick(position: Int)
     }

     fun setOnCategoryClickListener(listener : onCategoryClickListener){
         categoryClick = listener
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
         val view = LayoutInflater.from(parent.context)
             .inflate(R.layout.category_item, parent, false)

         return CategoryViewHolder(view,categoryClick)
     }



     override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
         holder.categoryTitle.text = categoryes[position].title
         if(categoryes.get(position).isSelected){
             holder.categoryBackground.setBackgroundResource(R.drawable.editext_background)
         }
         else
             holder.categoryBackground.setBackgroundResource(R.drawable.round_corner_background)
     }


     override fun getItemCount(): Int {
       return  categoryes.size
     }

 }