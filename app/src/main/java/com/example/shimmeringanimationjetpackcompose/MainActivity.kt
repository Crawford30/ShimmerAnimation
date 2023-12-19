package com.example.shimmeringanimationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

import com.example.shimmeringanimationjetpackcompose.compoments.AnimatedShimmer
import com.example.shimmeringanimationjetpackcompose.ui.theme.ShimmeringAnimationJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ShimmeringAnimationJetPackComposeTheme {
                var isLoading by remember {
                    mutableStateOf(true)
                }
                LaunchedEffect(key1 = true) {
                    delay(10000)
                    isLoading = false
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(20) {
                        AnimatedShimmer(
                            isLoading = isLoading,
                            contentAfterLoading = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = null,
                                        modifier = Modifier.size(100.dp)
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        text = "This is a long text to show that our shimmer display " +
                                                "is looking perfectly fine"
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShimmeringAnimationJetPackComposeTheme {
        Greeting("Android")
    }
}