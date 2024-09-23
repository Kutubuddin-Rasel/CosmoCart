package com.example.ecommerceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.cart
import com.example.ecommerceapp.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository): ViewModel() {
    val cartData=cartRepository.cartData
    val total=cartRepository.total
    init {
        getCartData()
    }
    fun getCartData(){
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.getCartData()
        }
    }
    fun removedata(cartid:String){
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.removedata(cartid)
        }
    }
    fun increase(cart: cart){
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.increase(cart)
        }
    }
    fun decrease(cart: cart){
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.decrease(cart)
        }
    }
}