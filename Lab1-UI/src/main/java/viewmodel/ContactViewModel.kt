package viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ContactUiState(
    val telefono: String = "",
    val direccion: String = "",
    val email: String = "",
    val pais: String = "",
    val ciudad: String = ""
)

class ContactViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState: StateFlow<ContactUiState> = _uiState.asStateFlow()

    fun updateTelefono(telefono: String) {
        _uiState.update { it.copy(telefono = telefono) }
    }

    fun updateDireccion(direccion: String) {
        _uiState.update { it.copy(direccion = direccion) }
    }

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePais(pais: String) {
        _uiState.update { it.copy(pais = pais) }
    }

    fun updateCiudad(ciudad: String) {
        _uiState.update { it.copy(ciudad = ciudad) }
    }

    // Validación de campos obligatorios para ContactDataActivity
    fun validateContactData(): Boolean {
        val state = _uiState.value
        // Los campos obligatorios son Teléfono, Email, País, Ciudad
        return state.telefono.isNotBlank() &&
                state.email.isNotBlank() &&
                state.pais.isNotBlank() &&
                state.ciudad.isNotBlank()
    }

    // Función para imprimir en Logcat
    fun logContactData() {
        val state = _uiState.value
        println("Información de contacto:")
        println("Teléfono: ${state.telefono}")
        println("Dirección: ${state.direccion}")
        println("Email: ${state.email}")
        println("País: ${state.pais}")
        println("Ciudad: ${state.ciudad}")
    }
}