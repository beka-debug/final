package com.example.market.data

import com.example.market.adapters.ProductRecyclerAdapter
import com.example.market.models.Category
import com.example.market.models.Product
import java.util.*

object CategoryData {

    val categoryList : List<Category> = listOf(
        Category(1,"car" , false),
        Category(2,"Pc",false),
        Category(3,"Home",false),
        Category(4,"Toy",false),
        Category(5,"Clothing",false),
    )

}