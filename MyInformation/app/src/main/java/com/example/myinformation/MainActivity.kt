package com.example.myinformation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFD2E8D4)
                ) {
                    MyInformationApp()
                }
            }
        }
    }
}

@Composable
fun MyInformationApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "", modifier = Modifier.padding(1.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.backend),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .background(Color(0xFF073042))
                    .padding(8.dp)
            )
            Text(
                text = stringResource(R.string.full_name),
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.title_dev),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006D3B)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactInfoRow(icon = Icons.Default.Phone, text = stringResource(R.string.phone_number))
            ContactInfoRow(icon = Icons.Default.Share, text = stringResource(R.string.social_handle))
            ContactInfoRow(icon = Icons.Default.Email, text = stringResource(R.string.email_address))
        }
    }
}

@Composable
fun ContactInfoRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF006D3B),
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}