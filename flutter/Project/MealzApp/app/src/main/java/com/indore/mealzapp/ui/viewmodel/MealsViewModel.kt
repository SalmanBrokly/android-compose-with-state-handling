package com.indore.mealzapp.ui.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indore.mealzapp.NetworkResult
import com.indore.mealzapp.datamodel.Category
import com.indore.mealzapp.repository.MealsRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class MealState(var mealList : List<Category> = emptyList(), val loading:Boolean=true)
class MealsViewModel(val repository: MealsRespository = MealsRespository.getInstance()):ViewModel(){

    private val _categoriesList = MutableLiveData(MealState())
    val categoryListState: LiveData<MealState> get() = _categoriesList

    /*
    *  private val _categoriesList = MutableStateFlow(MealState())
    val categoryListState: StateFlow<MealState> = _uiState.asStateFlow()
    * */
    init {
getMealz()
    }

    fun getMealz(){

        viewModelScope.launch(Dispatchers.Main) {
            Log.e(TAG, ": ApiCalled" )
        val response = repository.getMealsCategories()
            when(response){
                is NetworkResult.ApiSuccess->{
                    _categoriesList.value =_categoriesList.value?.copy(
                        mealList = response.data.categories,loading = false)
                }
                is NetworkResult.ApiError->{
                    Log.e(TAG, ":"+response.code)
                }
                is NetworkResult.ApiException->{
                    Log.e(TAG, ":"+response.e)
                }
            }
            _categoriesList.value =_categoriesList.value?.copy(loading = false)
        }
    }

    companion object{
        val TAG =MealsViewModel.javaClass.simpleName
    }

}
