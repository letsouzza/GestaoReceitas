package br.senai.sp.jandira.foodrecipe.screens.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DropDownItem(){
    DropdownMenuItem(
        text = {
            Text()
        },
        onClick = {}
    )
}

@Preview
@Composable
private fun DropDownItemPreview(){
    DropDownItem()
}