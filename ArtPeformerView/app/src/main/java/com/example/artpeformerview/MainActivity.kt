package com.example.artpeformerview

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artpeformerview.ui.ArtPerformerViewModel
import com.example.artpeformerview.ui.ArtPerformerUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Подключаем ViewModel архитектурного компонента
                    val artViewModel: ArtPerformerViewModel = viewModel()
                    val uiState by artViewModel.uiState.collectAsState()

                    // Вызываем отображение контента
                    ArtPerformerContent(
                        uiState = uiState,
                        onNextClick = { artViewModel.nextArtwork() },
                        onPrevClick = { artViewModel.previousArtwork() }
                    )
                }
            }
        }
    }
}

@Composable
fun ArtPerformerContent(
    uiState: ArtPerformerUiState,
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        Row(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                ArtWall(uiState.imageResourceId)
            }
            Column(
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ArtDescriptor(uiState.titleResourceId, uiState.artistResourceId)
                Spacer(modifier = Modifier.height(24.dp))
                DisplayControls(onPrevClick, onNextClick)
            }
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ArtWall(uiState.imageResourceId)
            Spacer(modifier = Modifier.height(32.dp))
            ArtDescriptor(uiState.titleResourceId, uiState.artistResourceId)
            Spacer(modifier = Modifier.height(24.dp))
            DisplayControls(onPrevClick, onNextClick)
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
            Text(text = "Previous")
        }
        Button(onClick = onNext, modifier = Modifier.weight(1f)) {
            Text(text = "Next")
        }
    }
}
