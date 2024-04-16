package com.senac.telanovaviagem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senac.telanovaviagem.components.LabelComponent
import com.senac.telanovaviagem.components.MyTopBar
import com.senac.telanovaviagem.ui.theme.TelaNovaViagemTheme
import com.senac.telanovaviagem.viewmodel.ViagemViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaNovaViagemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Myapp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Myapp(viagemViewModel: ViagemViewModel = viewModel()) {
    Scaffold(
        topBar = {
            MyTopBar()
        }
    ) {

        var showDatePickerDialog1 = remember {
            mutableStateOf(false)
        }
        var selectedDate1 = remember {
            mutableStateOf("")
        }
        val datePickerState1 = rememberDatePickerState()

        var showDatePickerDialog2 = remember {
            mutableStateOf(false)
        }
        var selectedDate2 = remember {
            mutableStateOf("")
        }
        val datePickerState2 = rememberDatePickerState()


        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {

            val viagemState = viagemViewModel.uiState.collectAsState()

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LabelComponent(labelResource = R.string.destino)
            }
            Row {
                OutlinedTextField(
                    value = viagemState.value.destino.toString(),
                    onValueChange = {},
                    modifier = Modifier
                        .weight(4f)
                        .padding(top = 10.dp)
                )
            }
            Row {
                LabelComponent(labelResource = R.string.tipo)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = true,
                    onClick = {},
                    modifier = Modifier
                        .weight(0.5f)
                )
                Text(
                    text = "Lazer",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .weight(1.5f)
                )
                RadioButton(
                    selected = false,
                    onClick = {},
                    modifier = Modifier
                        .weight(0.5f)
                )
                Text(
                    text = "Negócios",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .weight(1.5f)
                )
            }

            Row {
                LabelComponent(labelResource = R.string.datainicio)
            }

            Row {
                if (showDatePickerDialog1.value) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePickerDialog1.value = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                    datePickerState1
                                        .selectedDateMillis?.let { millis ->
                                            selectedDate1.value = millis.toBrazilianDateFormat()
                                        }
                                    showDatePickerDialog1.value = false
                                }) {
                                Text(text = "Escolher data")
                            }
                        }) {
                        DatePicker(state = datePickerState1)
                    }
                }
                OutlinedTextField(
                    value = selectedDate1.value,
                    onValueChange = { },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .onFocusChanged {
                            if (it.isFocused) {
                                showDatePickerDialog1.value = true
                            }
                        },
                    readOnly = true
                )
            }

            Row {
                LabelComponent(labelResource = R.string.datafinal)
            }

            Row {
                if (showDatePickerDialog2.value) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePickerDialog2.value = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                    datePickerState2
                                        .selectedDateMillis?.let { millis ->
                                            selectedDate2.value = millis.toBrazilianDateFormat()
                                        }
                                    showDatePickerDialog2.value = false
                                }) {
                                Text(text = "Escolher data")
                            }
                        }) {
                        DatePicker(state = datePickerState2)
                    }
                }
                OutlinedTextField(
                    value = selectedDate2.value,
                    onValueChange = { },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .onFocusChanged {
                            if (it.isFocused) {
                                showDatePickerDialog2.value = true
                            }
                        },
                    readOnly = true
                )
            }

            Row {
                LabelComponent(labelResource = R.string.orcamento)
            }

            Row {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .weight(4f)
                        .padding(top = 10.dp)
                )
            }
            
            Row {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(top = 45.dp)
                        .weight(2f)
                    ) {
                   Text(text = "Salvar") 
                }
            }

        }
    }
}


fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}



