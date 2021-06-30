package com.example.market.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.market.R

class ProductViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    val productImageView : ImageView
    val productHeader:TextView
    val productDescription:TextView

    init {
        productImageView = view.findViewById(R.id.productItemImage)
        productHeader = view.findViewById(R.id.productHeader)
        productDescription = view.findViewById(R.id.description)
    }

}