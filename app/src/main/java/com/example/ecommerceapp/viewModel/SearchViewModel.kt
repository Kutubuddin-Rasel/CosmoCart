package com.example.ecommerceapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {
    private val _search=MutableStateFlow("")
    val search:StateFlow<String> =_search
    val searchlist=searchRepository.searchlist

    fun setSearch(value:String){
        _search.value=value
    }
    fun searchData(value:String){
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getSearchData(value)
        }
    }
}