package co.edu.udea.compumovil.gr03_20252.lab1.ui.theme

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import viewmodel.PersonalViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataScreen(
    viewModel: PersonalViewModel,
    onNextClicked: () -> Unit // Callback para navegar a la siguiente pantalla
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val calendar = uiState.fechaNacimiento

    // Date Picker Dialog
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val newCalendar = Calendar.getInstance()
                newCalendar.set(year, month, dayOfMonth)
                viewModel.updateFechaNacimiento(newCalendar)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    // Lanzamos efecto cuando entra la pantalla
    LaunchedEffect(Unit) {
        keyboardController?.show() // Fuerza mostrar teclado al iniciar
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Información Personal", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.nombre,
            onValueChange = { viewModel.updateNombre(it) },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false,
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.apellido,
            onValueChange = { viewModel.updateApellido(it) },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false,
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Sexo:")
            RadioButton(
                selected = uiState.sexo == "Hombre",
                onClick = { viewModel.updateSexo("Hombre") }
            )
            Text("Hombre")
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = uiState.sexo == "Mujer",
                onClick = { viewModel.updateSexo("Mujer") }
            )
            Text("Mujer")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Fecha de Nacimiento
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Fecha de Nacimiento: ")
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { datePickerDialog.show() }) {
                Text("${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Grado de Escolaridad (Spinner)
        val grados = listOf("Primaria", "Secundaria", "Universitaria", "Otro")
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = uiState.gradoEscolaridad,
                onValueChange = {},
                readOnly = true,
                label = { Text("Grado de escolaridad") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                grados.forEach { grado ->
                    DropdownMenuItem(
                        text = { Text(grado) },
                        onClick = {
                            viewModel.updateGradoEscolaridad(grado)
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (viewModel.validatePersonalData()) {
                viewModel.logPersonalData()
                onNextClicked() // Llama al callback para navegar
            } else {
                // TODO: Mostrar un SnackBar o Toast al usuario indicando campos obligatorios
                println("ERROR: Campos obligatorios de Información Personal incompletos.")
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Siguiente")
        }
    }
}