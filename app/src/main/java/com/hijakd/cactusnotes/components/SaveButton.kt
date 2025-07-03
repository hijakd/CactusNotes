package com.hijakd.cactusnotes.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SaveButton(modifier: Modifier = Modifier, text: String = "Save", enabled: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(7.dp),
        elevation = ButtonDefaults.buttonElevation(7.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text)
    }
}