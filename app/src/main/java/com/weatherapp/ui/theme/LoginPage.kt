package com.weatherapp.ui.theme

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.MainActivity
import com.weatherapp.RegisterActivity

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
        Column {
            DataField(
                description = "Email",
                value = email,
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.size(24.dp))
            PasswordField(
                description = "Password",
                value = password,
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.size(24.dp))
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(modifier = modifier) {
                Button(
                    modifier = Modifier.padding(end = 25.dp),
                    onClick = {
                        Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()
                        activity?.startActivity(
                            Intent(activity, MainActivity::class.java).setFlags(
                                Intent.FLAG_ACTIVITY_SINGLE_TOP
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
            Spacer(modifier = Modifier.size(24.dp))
            Box(
                modifier = Modifier.clickable(onClick = {
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