package com.example.market

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.market.data.CategoryData
import com.example.market.models.Product
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.product_item.*
import java.io.File
import java.util.*


class CreateProductFragment : Fragment() {

     lateinit var createProductView : View
     lateinit var spiner : Spinner
     lateinit var galeryImage : ImageView
     lateinit var imageUri : Uri
     lateinit var saveButton : LinearLayout
     lateinit var database : DatabaseReference
     lateinit var currentProduct : Product
     lateinit var headerEditText : TextInputEditText
      lateinit var descriptionEditText : TextInputEditText

     val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
         result->
                if(result.resultCode == Activity.RESULT_OK){

                    imageUri =  result.data?.data!!
                    val imageName = UUID.randomUUID().toString()
                    val storRef = FirebaseStorage.getInstance().getReference("images/${imageName}")
                    storRef.putFile(imageUri).addOnSuccessListener {
                        currentProduct.imageUrl = "images/${imageName}"
                    }

                    galeryImage.setImageURI(imageUri)

                }
     }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        createProductView = inflater.inflate(R.layout.fragment_create_product, container, false)

        spiner = createProductView.findViewById(R.id.categorySpiner)
        galeryImage = createProductView.findViewById(R.id.galeryImage)
        saveButton = createProductView.findViewById(R.id.SaveButton)
        headerEditText = createProductView.findViewById(R.id.headerEditText)
        descriptionEditText = createProductView.findViewById(R.id.descriptionEditText)
        database = FirebaseDatabase.getInstance().getReference("Users")

        currentProduct = Product(UUID.randomUUID().toString(),null,null,null,null)


        val categoryresult : List<String> = CategoryData.categoryList.map { i-> i.title }
        spiner.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,categoryresult)
        spiner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener
          {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               currentProduct.categoryId = CategoryData.categoryList.get(position).id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(requireContext(), "please select something ", Toast.LENGTH_SHORT).show()
            }
        }

        galeryImage.setOnClickListener(){
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)
        }

        saveButton.setOnClickListener(){

            if(headerEditText.text!!.isNotEmpty() && descriptionEditText.text!!.isNotEmpty()) {

                try {
                    currentProduct.header = headerEditText.text.toString()
                    currentProduct.description = descriptionEditText.text.toString()

                    database.child(currentProduct.header.toString()).setValue(currentProduct)
                        .addOnSuccessListener {
                            headerEditText.text?.clear()
                            descriptionEditText.text?.clear()
                            Toast.makeText(
                                requireContext(),
                                "your Product added successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_SHORT).show()
                        }
                }
                catch (e:Exception){}
            }

        }

        return  createProductView
    }

}