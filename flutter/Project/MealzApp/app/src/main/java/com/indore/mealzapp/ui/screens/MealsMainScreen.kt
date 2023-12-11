package com.indore.mealzapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.indore.mealzapp.datamodel.Category
import com.indore.mealzapp.ui.ImageComponent
import com.indore.mealzapp.ui.theme.MealzAppTheme
import com.indore.mealzapp.ui.theme.shapes
import com.indore.mealzapp.ui.viewmodel.MealsViewModel

@Composable
fun MealsMainScreen(clickItem:(id:String)->Unit,mealsViewModel: MealsViewModel = viewModel()) {

    Box(modifier = Modifier.fillMaxSize()) {
        if (mealsViewModel.categoryListState.observeAsState().value?.loading == true) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        val list: List<Category> =
            mealsViewModel.categoryListState.observeAsState().value?.mealList.orEmpty()
        LazyColumn {
            item { Text(text = "Android Compose") }
            items(list) { item ->
                MealCategories(categories = item){
                    clickItem(it)
                }
            }
        }
    }
}

@Composable
fun MealCategories(categories: Category,clickItem:(id:String)->Unit){


        var  expend by remember{ mutableStateOf(false) }
        Card(
            shape = shapes.medium,
            border = BorderStroke(1.dp, Color.Blue),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp).clickable { clickItem(categories.idCategory) },

            ) {
            Row(modifier = Modifier.animateContentSize()
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                ImageComponent(categories.strCategoryThumb)
                Spacer(modifier = Modifier.size(10.dp))
                Column {
                    Row {
                        Text(modifier = Modifier.weight(0.75f), style = MaterialTheme.typography.headlineMedium, text = categories.strCategory)
                        Image(modifier = Modifier.clickable { expend =!expend }
                            .weight(0.25f)
                            .align(Alignment.CenterVertically),
                            imageVector =if(expend) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "")
                    }

                    Spacer(modifier = Modifier.size(1.dp))
                    CompositionLocalProvider (LocalContentColor provides Color.Gray){
                        Text(text = categories.strCategoryDescription,
                            style = MaterialTheme.typography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = if(expend) 10 else 2
                        )
                    }

                }


            }
        }
}


@Preview(showBackground = true)
@Composable
fun MainScreenAppPreview() {
    MealzAppTheme {
        MealsMainScreen({})
    }
}