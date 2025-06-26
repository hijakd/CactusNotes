package com.hijakd.cactusnotes.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@Composable
fun NoteInputText(modifier: Modifier = Modifier,
                  text: String,
                  label: String,
                  maxLine: Int = 1,
                  onTextChange: (String) -> Unit,
                  onImeAction: () -> Unit = {}) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val corners = 13.dp

    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier.clip(RoundedCornerShape(corners)),
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        maxLines = maxLine,
        shape = RoundedCornerShape(corners)
    )
}