package com.weatherapp

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.graphics.Paint.Align
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.ui.theme.WeatherAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginPage()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.height(400.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,

                )
        }
        Box() {
            Column {
                OutlinedTextField(
                    value = email,
                    label = {
                        Text(text = "Email")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { email = it }
                )
                Spacer(modifier = Modifier.size(24.dp))
                OutlinedTextField(
                    value = password,
                    label = { Text(text = "Password") },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.size(24.dp))
            }
        }
        Box(
            Modifier.padding(top= 30.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Row(modifier = modifier) {
                    Button(
                        modifier = Modifier
                            .padding(end=25.dp),
                        onClick = {
                            Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()
                            activity?.startActivity(
                                Intent(activity, MainActivity::class.java).setFlags(
                                    FLAG_ACTIVITY_SINGLE_TOP
                                )
                            )
                        },
                        enabled = email.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text("Login")
                    }
                    Button(
                        onClick = { email = ""; password = "" }
                    ) {
                        Text("Clear")
                    }
                }
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable(onClick = {
                            activity?.startActivity(
                                Intent(activity, RegisterActivity::class.java)
                            )
                        }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        text = "Sing up",
                        color = Color.Blue,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}