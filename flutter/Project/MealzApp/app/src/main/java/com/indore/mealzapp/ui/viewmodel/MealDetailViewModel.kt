package com.indore.mealzapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.indore.mealzapp.datamodel.Category
import com.indore.mealzapp.repository.MealsRespository
import com.indore.mealzapp.ui.NavRouteExtra
data class MealDetailState(val id: String="",val category: Category?=Category(), val loading:Boolean=true)
class MealDetailViewModel(savedStateHandle: SavedStateHandle) :ViewModel(){
    val repository: MealsRespository = MealsRespository.getInstance()
    val detailState =MutableLiveData(MealDetailState())
    val _detailState get() = detailState

    init {
        detailState.value =detailState.value?.copy(id = savedStateHandle.get<String>(NavRouteExtra.MEAL_DETAIL_ID) ?:"")
        getMealById()
    }

    fun getMealById(){
        detailState.value=detailState.value?.copy(category =
        repository.getMealCategory(detailState.value?.id?:""),
            loading = false)
    }


}