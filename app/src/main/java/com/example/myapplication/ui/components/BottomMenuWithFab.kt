package com.example.myapplication.ui.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.navigation.sealedRoutes.Screen


@Composable
fun BottomMenuWithFab(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit = { paddingValues ->

        //Contenido principal de la pantalla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Contenido principal")
        }
    }
) {
    val context = LocalContext.current
    val fabSize = 64.dp
    val cutoutRadius = 30.dp // El radio del recorte circular

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                // Superficie con bordes redondeados y recorte circular para el FAB
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .shadow(
                            elevation = 8.dp,
                            shape = BottomBarCircularCutoutShape(cutoutRadius),
                            clip = false
                        ),
                    shape = BottomBarCircularCutoutShape(cutoutRadius),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    tonalElevation = 6.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Sección izquierda
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null // Elimina el ripple
                                    ) {
                                        //Llamada al viewModel para importar el archivo
                                        Toast.makeText(context, "Importar", Toast.LENGTH_SHORT).show()
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FileOpen,
                                    contentDescription = "Home",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = "Cargar",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }

                        // Espacio central para el FAB
                        Spacer(modifier = Modifier.width(64.dp))

                        // Sección derecha
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null // Elimina el ripple
                                    ) {
                                        //Llamada al viewModel para abrir el exportador de archivos
                                        Toast.makeText(context, "Exportar", Toast.LENGTH_SHORT).show()
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FileCopy,
                                    contentDescription = "Home",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = "Exportar",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                }

                // FAB centrado
                FloatingActionButton(
                    onClick = {
                        // Llamada al viewModel para iniciar la cámara
                        Toast.makeText(context, "Camera", Toast.LENGTH_SHORT).show()
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 12.dp
                    ),
                    modifier = Modifier
                        .size(fabSize)
                        .align(Alignment.TopCenter)
                        .offset(y = (-28).dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = "Camera",
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}

// Shape personalizado con un recorte circular en el centro
class BottomBarCircularCutoutShape(private val cutoutRadius: androidx.compose.ui.unit.Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cutoutRadiusPx = with(density) { cutoutRadius.toPx() }
        val cornerRadius = with(density) { 16.dp.toPx() }

        // Crear el path para la forma con el recorte circular
        val path = Path().apply {
            // Esquina superior izquierda con borde redondeado
            arcTo(
                rect = Rect(
                    left = 0f,
                    top = 0f,
                    right = cornerRadius * 2,
                    bottom = cornerRadius * 2
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = true
            )

            // Línea superior hasta el inicio del recorte
            lineTo(size.width / 2 - cutoutRadiusPx, 0f)

            // Recorte circular perfecto
            arcTo(
                rect = Rect(
                    left = size.width / 2 - cutoutRadiusPx,
                    top = 0f - cutoutRadiusPx,
                    right = size.width / 2 + cutoutRadiusPx,
                    bottom = 2 * cutoutRadiusPx - cutoutRadiusPx
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )

            // Línea superior desde el fin del recorte hasta la esquina derecha
            lineTo(size.width - cornerRadius, 0f)

            // Esquina superior derecha con borde redondeado
            arcTo(
                rect = Rect(
                    left = size.width - cornerRadius * 2,
                    top = 0f,
                    right = size.width,
                    bottom = cornerRadius * 2
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Completar el contorno
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            lineTo(0f, cornerRadius)

            close()
        }

        return Outline.Generic(path)
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun BottomMenuWithFabPreview() {
    val navController = NavController(context = LocalContext.current)
    MaterialTheme {
        BottomMenuWithFab(navController)
    }
}