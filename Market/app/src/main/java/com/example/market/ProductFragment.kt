package com.example.market

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.market.adapters.CategoryRecyclerAdapter
import com.example.market.adapters.ProductRecyclerAdapter
import com.example.market.data.CategoryData
import com.example.market.dto.ProductDto
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_product.*


class ProductFragment : Fragment() {

    private  lateinit var productAdapter : ProductRecyclerAdapter
    private  lateinit var categoryAdapter : CategoryRecyclerAdapter
    private  lateinit var productRecycler : RecyclerView
    private  lateinit var categoryRecycler: RecyclerView
    private  lateinit var productView : View
    private  lateinit var productArrayList : ArrayList<ProductDto>
    private  lateinit var dataBase: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       productView = inflater.inflate(R.layout.fragment_product, container, false)
        productArrayList = arrayListOf<ProductDto>()
        setupCategoryRecycler()
        setupProductRecycler()

        return productView
    }


    private  fun setupCategoryRecycler(){
        categoryAdapter = CategoryRecyclerAdapter(CategoryData.categoryList)

        categoryAdapter.setOnCategoryClickListener(object : CategoryRecyclerAdapter.onCategoryClickListener{
            override fun onCategoryClick(position: Int) {
                val currentCategory = CategoryData.categoryList.get(position)
                val data = productArrayList.filter { i-> i.categoryId == currentCategory.id }

                CategoryData.categoryList.forEach{i-> i.isSelected = false}
                currentCategory.isSelected = true
                categoryAdapter.notifyDataSetChanged()

                productAdapter = ProductRecyclerAdapter(data as ArrayList<ProductDto>)
                productRecycler.adapter = productAdapter
            }

        })

        categoryRecycler = productView.findViewById(R.id.CategoryRecyclerView)

        categoryRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        categoryRecycler.adapter = categoryAdapter
    }

    private   fun setupProductRecycler(){
        dataBase = FirebaseDatabase.getInstance().getReference("Users")
        dataBase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               if(snapshot.exists()){
                   for(productSnapshot in snapshot.children){

                       val product = productSnapshot.getValue(ProductDto :: class.java)
                        productArrayList.add(product!!)
                   }

                   productAdapter = ProductRecyclerAdapter(productArrayList)
                   productRecycler = productView.findViewById(R.id.ProductRecyclerView)

                   productRecycler.layoutManager = LinearLayoutManager(requireContext())
                   productRecycler.adapter = productAdapter

               }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "No Product", Toast.LENGTH_SHORT).show()
            }
        })
    }


}