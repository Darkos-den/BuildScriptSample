package io.dynamax.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.TestTag
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import io.dynamax.core.Core
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            val result = Core().getTodo()
            runOnUiThread {
                setContent {
                    MaterialTheme {
                        MainView(result)
                    }
                }
            }
        }
    }
}

@Composable
fun MainView(todo: String) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = todo
    )
}

@Preview
@Composable
fun preview(){
    MainView("test")
}
