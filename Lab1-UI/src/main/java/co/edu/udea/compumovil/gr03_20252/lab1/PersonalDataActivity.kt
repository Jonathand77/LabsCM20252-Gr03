package co.edu.udea.compumovil.gr03_20252.lab1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import co.edu.udea.compumovil.gr03_20252.lab1.ui.theme.PersonalDataScreen
import co.edu.udea.compumovil.gr03_20252.lab1.ui.theme.LabsCM20252Gr03Theme
import viewmodel.PersonalViewModel

class PersonalDataActivity : ComponentActivity() {

    private val personalViewModel: PersonalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsCM20252Gr03Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalDataScreen(
                        viewModel = personalViewModel,
                        onNextClicked = {
                            // Cuando se presiona "Siguiente" y la validación es exitosa
                            navigateToContactData()
                        }
                    )
                }
            }
        }
    }

    private fun navigateToContactData() {
        val intent = Intent(this, ContactDataActivity::class.java)
        // Puedes pasar datos del PersonalViewModel a ContactDataActivity si es necesario
        // Por ejemplo, serializando el uiState o pasándolo a un ViewModel compartido si usas Hilt/Dagger
        startActivity(intent)
    }
}
