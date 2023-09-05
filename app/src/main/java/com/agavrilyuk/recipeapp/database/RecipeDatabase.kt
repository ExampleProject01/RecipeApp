package com.agavrilyuk.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.agavrilyuk.recipeapp.entities.Category
import com.agavrilyuk.recipeapp.entities.Meal
import com.agavrilyuk.recipeapp.entities.MealsItems
import com.agavrilyuk.recipeapp.entities.Recipes
import com.agavrilyuk.recipeapp.dao.RecipeDao
import com.agavrilyuk.recipeapp.entities.CategoryItems
import com.agavrilyuk.recipeapp.entities.converter.CategoryListConverter
import com.agavrilyuk.recipeapp.entities.converter.MealListConverter

@Database(entities = [Recipes::class, CategoryItems::class, Category::class, Meal::class, MealsItems::class],version = 1,exportSchema = false)
@TypeConverters(CategoryListConverter::class, MealListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{

        var recipesDatabase: RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
            if (recipesDatabase == null){
                recipesDatabase = Room.databaseBuilder(
                        context,
                        RecipeDatabase::class.java,
                        "recipe.db"
                ).build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}