package com.example.ecommerceapp.Admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val addProductRepository: addProductRepository) : ViewModel() {
//    fun setData(){
//        viewModelScope.launch(Dispatchers.IO) {
//            addProductRepository.getdata()
//        }
//    }
    fun setData(collection:String,category:String,description:String){
        viewModelScope.launch {
            addProductRepository.setDescription(collection,category,description)
        }
    }

}