package com.example.ecommerceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticatioinViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) :ViewModel() {
    val authstate=authenticationRepository.authState

    fun login(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.logIn(email,password)
        }
    }
    fun singIn(email:String,password:String,onclick:(value:Boolean)->Unit){
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.signIn(email,password){
                onclick(it)
            }
        }
    }
    fun logOut(){
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.logOut()
        }
    }
}





