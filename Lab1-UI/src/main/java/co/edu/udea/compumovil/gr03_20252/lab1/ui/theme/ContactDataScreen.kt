package co.edu.udea.compumovil.gr03_20252.lab1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDataScreen(
    viewModel: ContactViewModel,
    onFinishedClicked: () -> Unit // Callback para finalizar o ir a una pantalla de resumen
) {
    val uiState by viewModel.uiState.collectAsState()

    // Para el Autocomplete de País y Ciudad, usaremos ExposedDropdownMenuBox
    // En un proyecto real, cargarías estos datos desde una fuente de datos (API, base de datos, etc.)
    val paisesLatinoamerica = remember { mutableStateListOf(
        "Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba",
        "Ecuador", "El Salvador", "Guatemala", "Honduras", "México", "Nicaragua",
        "Panamá", "Paraguay", "Perú", "República Dominicana", "Uruguay", "Venezuela"
    ) }

    val ciudadesColombia = remember { mutableStateListOf(
        "Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Cúcuta",
        "Bucaramanga", "Pereira", "Manizales", "Santa Marta"
    ) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Información de Contacto", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.telefono,
            onValueChange = { viewModel.updateTelefono(it) },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.direccion,
            onValueChange = { viewModel.updateDireccion(it) },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(autoCorrect = false, capitalization = KeyboardCapitalization.Sentences)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Autocomplete de País
        var expandedPais by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedPais,
            onExpandedChange = { expandedPais = !expandedPais },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = uiState.pais,
                onValueChange = { newValue ->
                    viewModel.updatePais(newValue)
                    expandedPais = true // Mantener expandido mientras el usuario escribe
                },
                label = { Text("País") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPais) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            val filteringOptionsPais = paisesLatinoamerica.filter { it.contains(uiState.pais, ignoreCase = true) }
            if (filteringOptionsPais.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expandedPais,
                    onDismissRequest = { expandedPais = false }
                ) {
                    filteringOptionsPais.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                viewModel.updatePais(selectionOption)
                                expandedPais = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Autocomplete de Ciudad
        var expandedCiudad by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedCiudad,
            onExpandedChange = { expandedCiudad = !expandedCiudad },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = uiState.ciudad,
                onValueChange = { newValue ->
                    viewModel.updateCiudad(newValue)
                    expandedCiudad = true // Mantener expandido mientras el usuario escribe
                },
                label = { Text("Ciudad") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCiudad) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            val filteringOptionsCiudad = ciudadesColombia.filter { it.contains(uiState.ciudad, ignoreCase = true) }
            if (filteringOptionsCiudad.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expandedCiudad,
                    onDismissRequest = { expandedCiudad = false }
                ) {
                    filteringOptionsCiudad.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                viewModel.updateCiudad(selectionOption)
                                expandedCiudad = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (viewModel.validateContactData()) {
                viewModel.logContactData()
                onFinishedClicked() // Llama al callback para finalizar
            } else {
                // TODO: Mostrar un SnackBar o Toast al usuario indicando campos obligatorios
                println("ERROR: Campos obligatorios de Información de Contacto incompletos.")
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Siguiente")
        }
    }
}