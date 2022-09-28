package com.picpay.desafio.android.ui.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TestString() {
    Surface {
        ListTitle("Contatos")
    }
}

@Composable
fun ListTitle(title: String) {
    Text(text = title)
}

@Composable
@Preview
fun PreviewTesteString() {
    MaterialTheme {
        Surface {
            ListTitle("Contatos")
        }
    }
}