package com.indore.mealzapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.indore.mealzapp.datamodel.Category
import com.indore.mealzapp.ui.viewmodel.MealDetailViewModel

@Composable
fun MealDetailScreen( mealDetailViewModel:MealDetailViewModel= viewModel()){

Surface {
    val value =mealDetailViewModel._detailState.observeAsState()
    val category=value.value?.category?: Category()
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = "Meal Details Image"
        )
        Text(modifier = Modifier.fillMaxWidth(), text =category.strCategory?:"Salman",
            textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineSmall)
        Text(modifier = Modifier.fillMaxWidth(), text = category.strCategoryDescription?:"Description",
            textAlign = TextAlign.Center)
    }
}

}
@Preview
@Composable
fun MealDetailScreenPreview(){
//    MealDetailScreen(MealDetailViewModel)
}