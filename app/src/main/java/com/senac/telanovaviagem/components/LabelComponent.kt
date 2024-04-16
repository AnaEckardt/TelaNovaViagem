package com.senac.telanovaviagem.components
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowScope.LabelComponent (@StringRes labelResource: Int) {
    Text(
        text = stringResource(labelResource),
        fontSize = 22.sp,
        textAlign = TextAlign.Left,
        modifier = Modifier.weight(1f)
            .padding(end = 8.dp))
}