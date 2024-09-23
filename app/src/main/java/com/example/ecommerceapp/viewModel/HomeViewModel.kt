package com.example.ecommerceapp.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {
    private val _selectedImageUri= MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> =_selectedImageUri
    private val _username= MutableStateFlow("")
    val username: StateFlow<String> =_username
init {
    getImgerUrl()
    getuserdetais()
}
    fun getImgerUrl(){
        viewModelScope.launch(Dispatchers.IO) {
            val image=profileRepository.getImageUrl()
            if(image!=null){
                _selectedImageUri.value=image
            }
        }
    }
    fun getuserdetais(){
        viewModelScope.launch(Dispatchers.IO) {
            val username = profileRepository.getUserdetails()
            _username.value=username.username
        }
    }
}