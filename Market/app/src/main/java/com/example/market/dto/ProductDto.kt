package com.example.market.dto

import java.util.*

data class ProductDto (var id: String? = null,
                       var imageUrl : String? = null,
                       var header : String? = null,
                       var description : String? = null,
                       var categoryId: Int? = null)
