package br.senai.sp.jandira.foodrecipe.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.foodrecipe.model.DifficultLevel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.foodrecipe.R
import br.senai.sp.jandira.foodrecipe.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NivelDropdown(
    niveis: List<DifficultLevel>,
    onNivelSelecionado: (Int?) -> Unit
)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var selectedId by remember { mutableStateOf<Int?>(null) }

    // Box para conter o TextField e o Dropdown
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            // TextField que mostra a seleção atual e abre/fecha o menu
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
                value = selectedText,
                onValueChange = {},
                label = { Text(
                    text = stringResource(R.string.dificulty_level),
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFamily,
                    color = Color(0xFF261C09)
                )},
                shape = RoundedCornerShape(35.dp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    unfocusedContainerColor = Color(0xFFFFD972),
                    focusedContainerColor = Color(0xFFFFD972),
                    unfocusedBorderColor = Color(0xFF261C09),
                    focusedBorderColor = Color(0xFF261C09)
                )
            )

            // Menu dropdown com as opções
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                niveis.forEach { nivel ->
                    DropdownMenuItem(
                        text = { Text(
                            nivel.dificuldade,
                            color = Color(0xFF261C09),
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp
                        )},
                        onClick = {
                            selectedText = nivel.dificuldade
                            selectedId = nivel.id
                            expanded = false
                            onNivelSelecionado(nivel.id)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFDF87))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DropDownItemPreview(){
    //DropDownItem()
}