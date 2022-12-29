package com.example.recipe_app

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.recipe_app.databinding.ActivityCategoriesBinding
import okhttp3.*
import okio.IOException
import org.json.JSONArray


data class RecipeCategory(val id: String, val name: String)

class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun fetchRecipeCategories(callback: (List<RecipeCategory>) -> Unit) {
        val client = OkHttpClient()
        val url = "https://www.themealdb.com/api/json/v1/1/categories.php"
        val request = Request.Builder()
            .url(url)
            .build()
    }
/*
        client.newCall(request.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                print("Failure on the call")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body!!.string()
                val categories = mutableListOf<RecipeCategory>()
                val jsonArray = JSONArray(responseString)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val id = jsonObject.getString("idCategory")
                    val name = jsonObject.getString("strCategory")
                    val category = RecipeCategory(id, name)
                    categories.add(category)
                }
                callback(categories)
            }
        })
        )
    }
*/

}