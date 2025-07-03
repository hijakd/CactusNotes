package com.hijakd.cactusnotes.components

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hijakd.cactusnotes.ui.theme.AlertDark
import com.hijakd.cactusnotes.ui.theme.AlertLight
import com.hijakd.cactusnotes.ui.theme.CactusNotesTheme
import com.hijakd.cactusnotes.ui.theme.DarkGrey
import com.hijakd.cactusnotes.ui.theme.LightGrey
import com.hijakd.cactusnotes.ui.theme.Red
import com.hijakd.cactusnotes.ui.theme.White

@Composable
fun DefaultCategoriesDialog(modifier: Modifier = Modifier) {
    val dialogStatus = remember { mutableStateOf(false) }
    val ctx = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (!dialogStatus.value) {
            AlertDialog(
                onDismissRequest = { dialogStatus.value = false },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.Warning,
                            contentDescription = "Warning Icon",
                            modifier = modifier.size(30.dp),
                            tint = if (isSystemInDarkTheme()) White else Red
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Text(
                            text = "Add Default Categories?",
                            color = if (isSystemInDarkTheme()) AlertDark else AlertLight,
                            fontSize = 20.sp
                        )
                    }
                },
                text = {
                    Text(
                        text = "Do you wish to add the default list of categories to the database?",
                        color = if (isSystemInDarkTheme()) AlertDark else AlertLight,
                        fontSize = 18.sp
                    )
                },
                containerColor = if (isSystemInDarkTheme()) DarkGrey else LightGrey,
                shape = RoundedCornerShape(10.dp), confirmButton = {
                    Button(
                        onClick = {
                            dialogStatus.value = !dialogStatus.value
                            Toast.makeText(ctx, "Default Categories were added", Toast.LENGTH_SHORT).show()
                            /* TODO: add categories to database */
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("YES", color = MaterialTheme.colorScheme.primary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }, dismissButton = {
                    Button(
                        onClick = {
                            dialogStatus.value = !dialogStatus.value
                            Toast.makeText(ctx, "Nothing added", Toast.LENGTH_LONG).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("NO", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                })
        }
    }
}

@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    CactusNotesTheme {
        DefaultCategoriesDialog()
    }
}