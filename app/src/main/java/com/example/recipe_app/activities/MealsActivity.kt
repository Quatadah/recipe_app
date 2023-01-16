package com.example.recipe_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.R
import com.example.recipe_app.adapters.CategoriesAdapter
import com.example.recipe_app.adapters.Category
import com.example.recipe_app.adapters.Meal
import com.example.recipe_app.adapters.MealsAdapter
import com.example.recipe_app.services.MealService

class MealsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealsAdapter
    private val mealService = MealService()
    private var meals: List<Meal> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        val category = intent.getStringExtra("category")
        setTitle(category)

        recyclerView = findViewById(R.id.meals_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MealsAdapter(meals) {meal ->
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("idMeal", meal.idMeal)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        mealService.getMeals(category) { apiMeals ->
            runOnUiThread {
                if (apiMeals != null) {
                    meals = apiMeals
                    adapter.meals = meals
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e("Meals", "Unable to show meals")
                    // show an error message or default meals
                }
            }
        }

        supportActionBar?.hide()

    }
}