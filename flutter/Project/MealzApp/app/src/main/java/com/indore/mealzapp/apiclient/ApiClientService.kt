package com.indore.mealzapp.apiclient


import com.indore.mealzapp.datamodel.CategoriesListResponse

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object ApiClientService{
    private val retrofit=
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

val retrofitService:ApiClientInterface by lazy {
    retrofit.create(ApiClientInterface::class.java)
}
}

interface ApiClientInterface{
    @GET("categories.php")
    suspend fun mealCategories(): Response<CategoriesListResponse>
}