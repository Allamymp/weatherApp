package com.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.ui.theme.DataField
import com.weatherapp.ui.theme.PasswordField
import com.weatherapp.ui.theme.WeatherAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterPage()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Sign up",
            fontSize = 24.sp
        )
        DataField(
            description = "Username",
            value = username,
            onValueChange = { username = it }
        )
        Spacer(modifier = Modifier.size(24.dp))

        DataField(
            description = "Email",
            value = email,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.size(24.dp))

        PasswordField(
            description = "Password",
            value = password,
            onValueChange = { password = it })
        Spacer(modifier = Modifier.size(24.dp))

        DataField(description = "Confirm Password",
            value = confirmPassword,
            onValueChange = { confirmPassword = it })
        Spacer(modifier = Modifier.size(150.dp))

        Row(modifier = modifier) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Registered!", Toast.LENGTH_LONG).show()
                    activity?.startActivity(
                        Intent(activity, LoginActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                },
                enabled = email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword
            ) {
                Text("Sing up")
            }
            Button(
                onClick = { username = ""; email = ""; password = ""; confirmPassword = "" }
            ) {
                Text("Clear")
            }
        }
    }
}