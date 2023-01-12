package com.example.recipe_app.models

class Category : java.io.Serializable {
    var idCategory             : String? = null
    var strCategory            : String? = null
    var strCategoryThumb       : String? = null

    constructor(
        idCategory: String?,
        strCategory: String?,
        strCategoryThumb: String?,
    ) {
        this.idCategory = idCategory
        this.strCategory = strCategory
        this.strCategoryThumb = strCategoryThumb
    }

}