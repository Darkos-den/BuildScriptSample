package com.company.projectName.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.livedata.observeAsState
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.company.projectName.android.view.Initial

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by lazy {
        ViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(model = viewModel)
        }
    }
}

@Composable
fun App(model: ViewModel) {
    MaterialTheme {
        Scaffold(topAppBar = { MyAppBar() }) {
            // observe the viewmodel here. compose will recompose when it changes.
            model.viewState.observeAsState()
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
