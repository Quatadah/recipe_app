package com.example.recipe_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipe_app.R

data class Meal(val idMeal : String, val strMeal: String, val strMealThumb: String)


class MealsAdapter(var meals: List<Meal>, private val onCLick: (Meal) -> Unit) : RecyclerView.Adapter<MealsAdapter.MealViewHolder>() {


    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealImage: ImageView = itemView.findViewById(R.id.meal_image)
        val mealTitle: TextView = itemView.findViewById(R.id.meal_title)
        val mealButton: Button = itemView.findViewById(R.id.showRecipeButton)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.mealTitle.text = meal.strMeal
        holder.mealButton.setOnClickListener {
            onCLick(meal);
        }
        Glide.with(holder.mealImage.context)
            .load(meal.strMealThumb)
            .into(holder.mealImage)
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}

