package com.sandeep.diceroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.sandeep.diceroll.ui.theme.DiceRollTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollApp()
        }
    }
}

@Composable
fun DiceRollApp() {
    var diceState by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = getDiceImageResource(diceState)),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clickable { rollDice { newState -> diceState = newState } }
        )

        Spacer(modifier = Modifier.height(78.dp))

        Button(
            onClick = { rollDice { newState -> diceState = newState } },
            shape = CutCornerShape(20)
        ) {

            Text("Roll the Dice")

        }
    }
}


fun rollDice(updateDiceState: (Int) -> Unit) {
    var newState = 1
    var currentState: Int

    do {
        currentState = newState
        newState = (1..6).random()
    } while (currentState == newState)

    // Update the state to trigger recomposition
    updateDiceState(newState)
}

@Composable
fun getDiceImageResource(diceState: Int): Int {
    return when (diceState) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiceRollApp() {
    DiceRollApp()
}