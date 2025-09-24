package co.edu.udea.compumovil.gr03_20252.lab1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import co.edu.udea.compumovil.gr03_20252.lab1.ui.ContactDataScreen
import co.edu.udea.compumovil.gr03_20252.lab1.ui.theme.Theme // Asegúrate de que este es tu tema
import viewmodel.ContactViewModel

class ContactDataActivity : ComponentActivity() {

    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsCM20231Gr01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactDataScreen(
                        viewModel = contactViewModel,
                        onFinishedClicked = {
                            // Cuando se presiona "Siguiente" en ContactData y la validación es exitosa
                            Toast.makeText(this, "Datos guardados y loggeados!", Toast.LENGTH_LONG).show()
                            // Aquí podrías cerrar la actividad, navegar a una pantalla de resumen, etc.
                            finish() // Cierra esta actividad y regresa a PersonalDataActivity
                        }
                    )
                }
            }
        }
    }
}