package com.shivam.thursdaytrivia2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shivam.thursdaytrivia2.ui.theme.Thursdaytrivia2Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Thursdaytrivia2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Rockpaper()
                }
            }
        }
    }
}

fun scorer(faction: String, aaction: String): Int {
    // winner calculator 1 for player 0 for android 2 means a tie
    var win = 0
    if (faction == aaction)
        win = 2
    else if (faction == "Rock" && aaction == "Paper")
        win = 0
    else if (faction == "Rock" && aaction == "Scissor")
        win = 1
    else if (faction == "Paper" && aaction == "Rock")
        win = 1
    else if (faction == "Paper" && aaction == "Scissor")
        win = 0
    else if (faction == "Scissor" && aaction == "Paper")
        win = 1
    else if (faction == "Scissor" && aaction == "Rock")
        win = 0
    return win
}

fun genfora(): String {
    //Android choice generator
    val list = listOf("Rock", "Paper", "Scissor")
    val randomIndex = Random.nextInt(list.size)
    return list[randomIndex]
}

@Composable
fun Rockpbutton(bvlaue: String, onClick: () -> Unit) {
    // buttons generator
    Button(
        modifier = Modifier
            .height(108.dp)
            .width(108.dp)
            .padding(10.dp),
        onClick = onClick,
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(text = bvlaue)
    }
}

@Composable
fun Playeraction(playera: String, actionc: String) {
    //player and android choices display
    Text(
        text = playera,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
    Text(
        text = actionc,
        fontSize = 32.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Rockpaper() {
    //main compose
    val image = painterResource(id = R.drawable.rock)
    var faction by remember { mutableStateOf("Rock") }
    var aaction by remember { mutableStateOf("Paper") }
    var tscore by remember { mutableStateOf("0 / 0") }
    var pscore by remember { mutableStateOf(0) }
    var ascore by remember { mutableStateOf(0) }
    Column {
        //main column
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = "Score", fontSize = 30.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Text(
            text = tscore, fontSize = 50.sp, modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Row(modifier = Modifier.padding(top = 85.dp)) {
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Playeraction(playera = "You Chose", actionc = faction)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Playeraction(playera = "Android chose", actionc = aaction)
            }

        }
        Row(modifier = Modifier.padding(top = 70.dp)) {
            Column(
                Modifier
                    .fillMaxWidth(0.33f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Rock") {
                    faction = "Rock"
                    aaction = genfora()
                    val win = scorer(faction, aaction)
                    if (win == 1)
                        pscore++
                    else if (win == 0)
                        ascore++
                    tscore = "$pscore / $ascore"
                }
            }
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Paper") {
                    faction = "Paper"
                    aaction = genfora()
                    val win = scorer(faction, aaction)
                    if (win == 1)
                        pscore++
                    else if (win == 0)
                        ascore++
                    tscore = "$pscore / $ascore"
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Rockpbutton(bvlaue = "Scissor") {
                    faction = "Scissor"
                    aaction = genfora()
                    val win = scorer(faction, aaction)
                    if (win == 1)
                        pscore++
                    else if (win == 0)
                        ascore++
                    tscore = "$pscore / $ascore"
                }
            }

        }
        Row(
            verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "#JetpackCompose",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Thursdaytrivia2Theme {
        Rockpaper()
    }
}