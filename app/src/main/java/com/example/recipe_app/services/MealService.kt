package com.example.recipe_app.services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.recipe_app.adapters.Category
import com.example.recipe_app.adapters.Meal
import com.example.recipe_app.models.Recipe
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
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
    var URL_MEALS           = "https://www.themealdb.com/api/json/v1/1/filter.php?c={strCategory}"
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

    fun getMeals(category: String?, callback: (List<Meal>?) -> Unit) {
        val url = category?.let { URL_MEALS.replace("{strCategory}", it) }
        if (url != null) {
            callbackBuilder.GET(url, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("Okhttp", "Failure on fetching meals")
                    callback(null)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseString = response.body?.string()
                    val mealsResponse = Gson().fromJson(responseString, Meals::class.java)
                    val meals = mealsResponse?.meals?.map {
                        Meal(it.idMeal, it.strMeal, it.strMealThumb)
                    }
                    callback(meals)
                }
            })
        }
    }

    fun getRecipe(idMeal: String?, callback: (Recipe?) -> Unit) {
        val url = idMeal?.let { RECIPE.replace("{idMeal}", it) }
        if (url != null) {
            callbackBuilder.GET(url, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("Okhttp", "Failure on fetching Recipe")
                    callback(null)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseString: String? = response.body?.string()
                    val mealArray = JSONObject(responseString).getJSONArray("meals")
                    val mealObject = mealArray.getJSONObject(0)
                    var listIngredients = ""
                    for (i in 1..20) {
                        val ingredients = mealObject.getString("strIngredient$i")
                        val measures = mealObject.getString("strMeasure$i")
                        if (ingredients != "") {
                            listIngredients += measures + " " + ingredients + "\n"
                        }
                    }
                    val recipe = Recipe(
                        mealObject.getString("idMeal"),
                        mealObject.getString("strMeal"),
                        mealObject.getString("strInstructions"),
                        mealObject.getString("strMealThumb"),
                        mealObject.getString("strYoutube"),
                        listIngredients,
                    )
                    callback(recipe)
                }
            })
        }

    }



}
