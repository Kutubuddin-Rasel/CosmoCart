package com.example.ecommerceapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.Men_Women_Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SareeViewModel @Inject constructor(private val menWomenRepository: Men_Women_Repository): ViewModel() {
    val datalist=menWomenRepository.datalist
init {
    getData()
}
    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            menWomenRepository.getData("Women","Saree")
        }
    }
}