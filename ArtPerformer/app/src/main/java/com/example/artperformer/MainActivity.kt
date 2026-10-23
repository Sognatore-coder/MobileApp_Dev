package com.example.artperformer
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var currentStep by remember { mutableStateOf(1) }

    // Получение текущей конфигурации для адаптивного макета
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val imageRes = when (currentStep) {
        1 -> R.drawable.xxx
        2 -> R.drawable.phara
        else -> R.drawable.trip
    }

    val titleRes = when (currentStep) {
        1 -> R.string.art1_release
        2 -> R.string.art2_release
        else -> R.string.art3_release
    }

    val artistRes = when (currentStep) {
        1 -> R.string.art1_artist
        2 -> R.string.art2_artist
        else -> R.string.art3_artist
    }

    // Навигация по кнопкам
    val onNext = {
        currentStep = if (currentStep < 3) currentStep + 1 else 1
    }
    val onPrevious = {
        currentStep = if (currentStep > 1) currentStep - 1 else 3
    }

    if (isLandscape) {
        // Верстка для широкого экрана
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                ArtWall(imageRes)
            }
            Column(
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ArtDescriptor(titleRes, artistRes)
                Spacer(modifier = Modifier.height(24.dp))
                DisplayControls(onPrevious, onNext)
            }
        }
    } else {
        // Верстка для портретного экрана
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ArtWall(imageRes)
            Spacer(modifier = Modifier.height(32.dp))
            ArtDescriptor(titleRes, artistRes)
            Spacer(modifier = Modifier.height(24.dp))
            DisplayControls(onPrevious, onNext)
        }
    }
}

@Composable
fun ArtWall(imageRes: Int) {
    Box(
        modifier = Modifier
            .shadow(8.dp)
            .border(4.dp, Color.Black)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(width = 280.dp, height = 360.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ArtDescriptor(titleRes: Int, artistRes: Int) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFECEFF1)),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = titleRes),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = stringResource(id = artistRes),
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun DisplayControls(onPrevious: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = onPrevious, modifier = Modifier.weight(1f)) {
            Text(text = stringResource(R.string.btn_previous))
        }
        Button(onClick = onNext, modifier = Modifier.weight(1f)) {
            Text(text = stringResource(R.string.btn_next))
        }
    }
}