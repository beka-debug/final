package com.example.market.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.market.R
import com.example.market.adapters.viewHolders.ProductViewHolder
import com.example.market.dto.ProductDto
import com.example.market.models.Product
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class ProductRecyclerAdapter(private  val products : ArrayList<ProductDto>) : RecyclerView.Adapter<ProductViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)

        return ProductViewHolder(view)
    }



    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       holder.productHeader.text = products[position].header
        holder.productDescription.text = products[position].description

        var storageRef = FirebaseStorage.getInstance().reference.child(products.get(position).imageUrl.toString())
        val localfile = File.createTempFile("tempImage","jpg")

        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            holder.productImageView.setImageBitmap(bitmap)
        }

    }

    override fun getItemCount(): Int {
      return products.size
    }


}