package com.example.recipe_app.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app.R
import com.example.recipe_app.activities.CategoriesActivity
import org.w3c.dom.Text

data class Category(val strCategory: String)


class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var categories: List<Category> = emptyList()

    fun setData(categories: List<Category>){
        this.categories = categories
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName : Button = itemView.findViewById(R.id.strCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (categories.size > position) {
            val category = categories[position]
            holder.categoryName.text = category.strCategory
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
