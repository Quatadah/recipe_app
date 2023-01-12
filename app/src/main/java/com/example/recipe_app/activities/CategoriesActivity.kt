package com.example.recipe_app.activities

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.recipe_app.R
import com.example.recipe_app.adapters.CategoriesAdapter
import com.example.recipe_app.adapters.Category
import com.example.recipe_app.databinding.ActivityCategoriesBinding
import com.example.recipe_app.services.MealService
import okhttp3.*
import okio.IOException
import org.json.JSONArray


class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var categories: List<Category>
    private lateinit var adapter: CategoriesAdapter
    private val mealService = MealService();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CategoriesAdapter()
        recyclerView.adapter = adapter
        mealService.getCategories { apiCategories ->
            runOnUiThread {
                if (apiCategories != null) {
                    adapter.setData(apiCategories)
                    Log.i("categoriesActivity", apiCategories.toString())
                } else {
                    Log.e("Categories", "Unable to show categories")
                    val category1 = Category("Breakfast")
                    val category2 = Category("Lunch")
                    val category3 = Category("Dinner")
                    val category4 = Category("Desserts")
                    adapter.setData(listOf(category1, category2, category3, category4))
                }
            }
        }

        supportActionBar?.hide()
    }


    fun getCategories() : List<Category> {
        var categories = emptyList<Category>();
        mealService.getCategories { apiCategories ->
            if (apiCategories != null) {
                categories = apiCategories
                Log.i("categoriesActivity", categories.toString())
            } else {
                Log.e("Categories", "Unable to show categories")
                val category1 = Category("Breakfast")
                val category2 = Category("Lunch")
                val category3 = Category("Dinner")
                val category4 = Category("Desserts")
                categories = listOf(category1, category2, category3, category4)
            }
        }
        return categories;
    }

}