package com.indore.profilecardlayout

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.codingtroops.profilecardlayout.UserProfile
import com.codingtroops.profilecardlayout.userProfileList
import com.indore.profilecardlayout.ui.theme.ProfileCardLayoutTheme
import com.indore.profilecardlayout.ui.theme.lighGreen200
import com.indore.profilecardlayout.ui.theme.shapes


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardLayoutTheme() {
                MainScreen()
            }

        }
    }


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(topBar = {AppBar()},  content = { innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
        LazyColumn{items(userProfileList){ profilelistItem->
                ProfileCard(profilelistItem)}
            }
        }
    })
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(){
TopAppBar(navigationIcon = { Icon(Icons.Default.Home, contentDescription = "" ) }, title = { Text(text = "My Listing Msg") })
}

@Composable
fun ProfileCard(item:UserProfile) {
    Card(
        shape = shapes.medium,
        border = BorderStroke(1.dp, lighGreen200),
       elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(10.dp),

    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            ProfileImage(item.pictureUrl,item.status)
            Spacer(modifier = Modifier.size(10.dp))
            ProfileContent(item.name,item.status)
        }

    }
}

@Composable
fun ProfileImage(image: String, isOnline:Boolean) {
    Card(shape = CircleShape,
        modifier = Modifier
            .size(72.dp)
            .border(2.dp, if (isOnline) Color.Green else lighGreen200, CircleShape)
    ){
        val painter = rememberAsyncImagePainter(image)
        /*AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.picture_frame),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )*/
         Image(modifier = Modifier
            .size(72.dp)
            .clip(CircleShape)
            .border(2.dp, if (isOnline) Color.Green else lighGreen200, CircleShape),
            painter = painter,
            contentScale = ContentScale.Crop,
             contentDescription = "My profile image")
    }


}
@Composable
fun ProfileContent(name:String,isOnline:Boolean) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(style = MaterialTheme.typography.headlineSmall, text = name)
        CompositionLocalProvider(LocalContentColor provides Color.Gray) {
            Text(style = MaterialTheme.typography.bodySmall,text = if (isOnline)"Active Now" else "Offline" )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileCardLayoutTheme() {
        MainScreen()
    }
}