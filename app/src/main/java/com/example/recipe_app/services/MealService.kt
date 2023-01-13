package com.example.recipe_app.services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.recipe_app.adapters.Category
import com.example.recipe_app.adapters.Meal
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class CallbackBuilder{
    var client = OkHttpClient()

    fun GET(url: String, callback: Callback): Call{
        val request = Request.Builder()
            .url(url)
            .build()
        val call = client.newCall(request)
        call.enqueue(callback)
        return call;
    }
}

data class Categories(val categories: List<Category>)
data class Meals(val meals: List<Meal>)

class MealService {
    var URL_CATEGORIES      = "https://www.themealdb.com/api/json/v1/1/categories.php"
    var MEALS           = "https://www.themealdb.com/api/json/v1/1/filter.php?c={strCategory}"
    var SEARCH_MEAL     = "https://www.themealdb.com/api/json/v1/1/search.php?s={strMeal}"
    var RECIPE          = "https://www.themealdb.com/api/json/v1/1/lookup.php?i={idMeal}"
    var RANDOMRECIPE    = "https://www.themealdb.com/api/json/v1/1/random.php"

    private var callbackBuilder = CallbackBuilder();


    fun getCategories(callback: (List<Category>?) -> Unit) {
        callbackBuilder.GET(URL_CATEGORIES, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Okhttp", "Failure on fetching categories")
            }
            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body?.string()
                val categories = Gson().fromJson(responseString, Categories::class.java)
                val categoryList = categories?.categories?.map { Category(it.strCategory) }
                callback(categoryList)
            }
        })
    }

    fun getMeals(category: String, callback: (List<Meal>?) -> Unit) {
        val url = MEALS.replace("{strCategory}", category)
        callbackBuilder.GET(url, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Okhttp", "Failure on fetching meals")
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body?.string()
                val mealsResponse = Gson().fromJson(responseString, Meals::class.java)
                val meals = mealsResponse?.meals?.map {
                    Meal(it.strMeal, it.strMealThumb)
                }
                callback(meals)
            }
        })
    }



}
