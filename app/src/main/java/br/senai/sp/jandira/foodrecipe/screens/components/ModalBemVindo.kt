package br.senai.sp.jandira.foodrecipe.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.foodrecipe.ui.theme.gorditasFamily
import br.senai.sp.jandira.foodrecipe.ui.theme.poppinsFamily

@Composable
fun DialogWelcome(
    onDismissRequest: () -> Unit = {},
    onNavigate: () -> Unit = {},
    dialogTitle: String = "teste",
    dialogText: String = "teste",
    dialogTextSpecial: String = "teste",
    icon: ImageVector = Icons.Default.Info,
) {
    AlertDialog(
        modifier = Modifier
            .width(300.dp)
            .height(480.dp),
        containerColor = Color(0xFFFFDF87),
        onDismissRequest = { onDismissRequest() },
        confirmButton = {},
        dismissButton = {},
        title = null,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = dialogTitle,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = gorditasFamily,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF241508)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = dialogText,
                        fontSize = 22.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF241508)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = dialogTextSpecial,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFamily,
                        textAlign = TextAlign.Center,
                        color = Color(0xFFB15200)
                    )
                }

                // Botão no canto inferior direito
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    IconButton(
                        onClick = {
                            onNavigate()
                            onDismissRequest()
                        }
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Fechar",
                            modifier = Modifier.size(90.dp),
                            tint = Color(0xFF241508)
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun DialogWelcomePreview(){
    DialogWelcome()
}