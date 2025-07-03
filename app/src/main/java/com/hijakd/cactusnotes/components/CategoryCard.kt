package com.hijakd.cactusnotes.components

import android.R.attr.category
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hijakd.cactusnotes.model.Category
import com.hijakd.cactusnotes.ui.theme.DarkGrey
import com.hijakd.cactusnotes.ui.theme.LightSurface
import com.hijakd.cactusnotes.ui.theme.Red


//@Preview(showBackground = true, backgroundColor = 0xFF0000FF)
@Composable
fun CategoryCard(modifier: Modifier = Modifier,
                 category: Category,
                 onCardClicked: (Category) -> Unit = {}) {
    var cardExpanded by remember { mutableStateOf(false) }
    val categoryName: String = category.name
//    var cardColor by remember { mutableStateOf(LightSurface) }

    Card(
        modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = 5.dp, vertical = 3.dp)
                .clickable { onCardClicked(category) },
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
//        colors = CardDefaults.cardColors(cardColor),
        elevation = CardDefaults.cardElevation(7.dp)
    ) {
        Surface(modifier = modifier.fillMaxWidth().padding(horizontal = 7.dp, vertical = 4.dp).clickable{cardExpanded = !cardExpanded}) {
            Column {
                Row {
                    Spacer(modifier = Modifier.size(5.dp))
                    if (categoryName.isEmpty()) { // for testing
                        Text("cactus?!?")
                    } else {
                        Text(text = categoryName)
                    }
                }
                if (cardExpanded) {
                    Spacer(modifier = Modifier.size(5.dp))
                    Row(
                        modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            Icons.Rounded.Edit,
                            contentDescription = "edit icon",
                            modifier
                                    .size(20.dp)
                                    .clickable { TODO("add category edit") },
//                                    .clickable { cardColor = Red },
                            tint = DarkGrey
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = "delete icon",
                            modifier
                                    .size(20.dp)
                                    /*.clickable { TODO("add category delete") }*/,
                            tint = DarkGrey
                        )
                        Spacer(modifier = Modifier.width(13.dp))
                    }

                }
            }

        }
    }
}