package com.example.recipe_app.models

class Recipe {
    var idMeal : String? = null
    var strMeal : String? = null
    var strInstruction : String? = null
    var strMealThumb : String? = null
    var strYoutube : String? = null
    var listIngredients : String? = null

    constructor(
        idMeal : String?,
        strMeal : String?,
        strInstruction : String?,
        strMealThumb : String?,
        strYoutube : String?,
        listIngredients : String?,
    ) {
        this.idMeal = idMeal
        this.strMeal = strMeal
        this.strMealThumb = strMealThumb
        this.strInstruction = strInstruction
        this.strMealThumb = strMealThumb
        this.strYoutube = strYoutube
        this.listIngredients = listIngredients
    }
}