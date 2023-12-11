package com.indore.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.indore.mealzapp.datamodel.Category
import com.indore.mealzapp.ui.screens.MealDetailScreen
import com.indore.mealzapp.ui.screens.MealsMainScreen

import com.indore.mealzapp.ui.theme.MealzAppTheme
import com.indore.mealzapp.ui.viewmodel.MealDetailViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                    Navigation()
            }
        }
    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.MEALMAINSCREEN){
        composable(route=NavRoute.MEALMAINSCREEN){
            MealsMainScreen(clickItem ={
                navController.navigate("${NavRoute.MEALDETAILSSCREEN}/$it")
            })
        }

        composable(route= "${NavRoute.MEALDETAILSSCREEN}/{${NavRouteExtra.MEAL_DETAIL_ID}}", arguments = listOf(
            navArgument(name = NavRouteExtra.MEAL_DETAIL_ID){type = NavType.StringType},
        )){
            val id =it.arguments?.getString(NavRouteExtra.MEAL_DETAIL_ID)

            MealDetailScreen()
        }
    }
}

