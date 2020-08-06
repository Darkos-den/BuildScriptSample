package com.company.projectName.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.company.projectName.android.home.HomeScreen
import com.company.projectName.android.home.view.Initial

class MainActivity : AppCompatActivity() {

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeScreen().presenter.viewState.liveValue.observe(this, Observer {
            setContent {
                App(it)
            }
        })
    }
}

@ExperimentalStdlibApi
@Composable
fun App(state: @Composable() () -> Unit) {
    MaterialTheme {
        Scaffold(topAppBar = { MyAppBar() }) {
            // observe the viewmodel here. compose will recompose when it changes.
            state()
        }
    }
}

@Composable
fun MyAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "My app"
            )
        },
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        elevation = 12.dp
    )
}

@Preview
@Composable
fun preview() {
    MaterialTheme {
        Scaffold(topAppBar = { MyAppBar() }) {
            Initial()
        }
    }
}
