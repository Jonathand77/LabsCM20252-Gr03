package viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar

data class PersonalUiState(
    val nombre: String = "",
    val apellido: String = "",
    val sexo: String = "Hombre", // "Hombre" o "Mujer"
    val fechaNacimiento: Calendar = Calendar.getInstance(),
    val gradoEscolaridad: String = "Primaria" // Primaria, Secundaria, Universitaria, Otro
)

class PersonalViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PersonalUiState())
    val uiState: StateFlow<PersonalUiState> = _uiState.asStateFlow()

    fun updateNombre(nombre: String) {
        _uiState.update { it.copy(nombre = nombre) }
    }

    fun updateApellido(apellido: String) {
        _uiState.update { it.copy(apellido = apellido) }
    }

    fun updateSexo(sexo: String) {
        _uiState.update { it.copy(sexo = sexo) }
    }

    fun updateFechaNacimiento(fecha: Calendar) {
        _uiState.update { it.copy(fechaNacimiento = fecha) }
    }

    fun updateGradoEscolaridad(grado: String) {
        _uiState.update { it.copy(gradoEscolaridad = grado) }
    }

    // Validación de campos obligatorios
    fun validatePersonalData(): Boolean {
        return _uiState.value.nombre.isNotBlank() && _uiState.value.apellido.isNotBlank()
        // La fecha de nacimiento y el grado de escolaridad siempre tendrán un valor por defecto
        // o uno seleccionado, por lo que no necesitan una validación isNotBlank() aquí
    }

    // Función para imprimir en Logcat
    fun logPersonalData() {
        val state = _uiState.value
        val day = state.fechaNacimiento.get(Calendar.DAY_OF_MONTH)
        val month = state.fechaNacimiento.get(Calendar.MONTH) + 1 // Meses son base 0
        val year = state.fechaNacimiento.get(Calendar.YEAR)

        println("Información personal: ${state.nombre} ${state.apellido}")
        println("${state.sexo} (Suponiendo que el usuario seleccionó ${state.sexo})")
        println("Nació el $day/$month/$year")
        println(state.gradoEscolaridad)
    }
}
