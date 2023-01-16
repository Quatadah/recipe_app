package com.example.recipe_app.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recipe_app.R
import com.bumptech.glide.Glide
import com.example.recipe_app.models.Recipe
import com.example.recipe_app.services.MealService

class RecipeActivity : AppCompatActivity() {
    private val mealService = MealService()
    private lateinit var recipe : Recipe
    private lateinit var textViewIngredients: TextView
    private lateinit var textViewInstructions: TextView
    private lateinit var image : ImageView
    private lateinit var youtubeButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        textViewIngredients=findViewById(R.id.ingredients)
        textViewInstructions=findViewById(R.id.instructions)
        image=findViewById(R.id.imageView)
        youtubeButton=findViewById(R.id.youtubeButton)

        val idMeal = intent.getStringExtra("idMeal")
        mealService.getRecipe(idMeal) { apiRecipe ->
            runOnUiThread {
                if (apiRecipe != null) {
                    recipe = apiRecipe
                    textViewIngredients.text = recipe.listIngredients
                    textViewInstructions.text = recipe.strInstruction
                    youtubeButton.setOnClickListener {
                        val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.strYoutube))
                        startActivity(youtubeIntent)
                    }

                    Glide.with(this)
                        .load(recipe.strMealThumb)
                        .into(image)
                } else {
                    Log.e("Recipe", "Unable to show Recipe")
                    // show an error message or default meals
                }
            }
        }
        supportActionBar?.hide()
    }
}