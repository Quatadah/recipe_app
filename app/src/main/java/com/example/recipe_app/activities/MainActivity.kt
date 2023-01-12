package com.example.recipe_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.recipe_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exploreButton.setOnClickListener {
            Log.i("info", "here fails")
            val intent = Intent(applicationContext, CategoriesActivity::class.java)
            Log.i("info", "should work")
            startActivity(intent)
        }

        supportActionBar?.hide()
    }

}