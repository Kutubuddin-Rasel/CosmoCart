package com.example.ecommerceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.cart
import com.example.ecommerceapp.repository.AddToCartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddtoCartViewModel @Inject constructor(private val addToCartRepository: AddToCartRepository): ViewModel() {
    fun addTOCart(cart: cart){
        viewModelScope.launch(Dispatchers.IO) {
            addToCartRepository.addToCart(cart)
        }
    }
}