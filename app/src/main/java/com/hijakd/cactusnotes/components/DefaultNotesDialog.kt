package com.hijakd.cactusnotes.components

import android.util.Log
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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hijakd.cactusnotes.database.DummyNotes
import com.hijakd.cactusnotes.model.Note
import com.hijakd.cactusnotes.ui.theme.AlertDark
import com.hijakd.cactusnotes.ui.theme.AlertLight
import com.hijakd.cactusnotes.ui.theme.DarkGrey
import com.hijakd.cactusnotes.ui.theme.LightGrey
import com.hijakd.cactusnotes.ui.theme.Red
import com.hijakd.cactusnotes.ui.theme.White

@Composable
fun DefaultNotesDialog(
        modifier: Modifier = Modifier,
        loadSampleNotesStatus: MutableState<Boolean>,
        defaultNotesDialogStatus: MutableState<Boolean>,
        notesList: MutableList<Note>,
) {
    val ctx = LocalContext.current
    val TAG = "dialog"

//    Log.d(TAG, "DefaultNotesDialog: displaying")
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AlertDialog(
            onDismissRequest = { false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Rounded.Warning,
                        contentDescription = "warning icon",
                        modifier = modifier.size(30.dp),
                        tint = if (isSystemInDarkTheme()) White else Red
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = "Add Sample Notes?",
                        color = if (isSystemInDarkTheme()) AlertDark else AlertLight,
                        fontSize = 20.sp
                    )
                }
            },
            text = {
                Text(
                    text = "Do you wish to add the sample notes to the database?",
                    color = if (isSystemInDarkTheme()) AlertDark else AlertLight,
                    fontSize = 18.sp
                )
            },
            containerColor = if (isSystemInDarkTheme()) DarkGrey else LightGrey,
            shape = RoundedCornerShape(10.dp),
            confirmButton = {
                Button(
                    onClick = {
                        loadSampleNotesStatus.value = true
                        defaultNotesDialogStatus.value = !defaultNotesDialogStatus.value
                        Toast.makeText(ctx, "Sample Notes were added to the database", Toast.LENGTH_SHORT).show()
                        /* TODO: add notes to database */
//                            var idx = 0
                        for (note in DummyNotes().loadNotes()) {
                            notesList.add(note)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = White),
                    modifier = modifier.width(100.dp)
                ) {
                    Text("Yes", color = MaterialTheme.colorScheme.primary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        loadSampleNotesStatus.value = false
                        defaultNotesDialogStatus.value = !defaultNotesDialogStatus.value
                        Toast.makeText(ctx, "Nothing added", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = White),
                    modifier = modifier.width(100.dp)
                ) {
                    Text(text = "NO", color = Red, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                } // END of Button
            }
        )
    } // END of Column
}