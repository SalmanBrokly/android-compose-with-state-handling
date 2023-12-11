package com.indore.mealzapp.repository

import com.indore.mealzapp.NetworkResult
import com.indore.mealzapp.apiclient.ApiClientService.retrofitService
import com.indore.mealzapp.datamodel.CategoriesListResponse
import com.indore.mealzapp.datamodel.Category

import com.indore.mealzapp.handleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MealsRespository() {

   lateinit var tempCachedList :List<Category>
   suspend fun getMealsCategories():NetworkResult<CategoriesListResponse> =
      handleApi { withContext(Dispatchers.IO){
         val response= retrofitService.mealCategories()
         tempCachedList=response.body()?.categories.orEmpty()
      return@withContext response
      }}

    fun getMealCategory(id:String):Category? =
       tempCachedList?.firstOrNull { it.idCategory == id }

   companion object{

      @Volatile
      private var instance:MealsRespository?=null

      fun getInstance() = instance?:synchronized(this){
         instance ?:MealsRespository().also {
            instance=it
         }
      }
   }
}
