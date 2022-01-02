
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.pucrs.sds.lib.AesAlgorithm
import com.pucrs.sds.lib.AesMode

@Composable
@Preview
fun App() {
    var textDecrypted by remember { mutableStateOf("") }
    var textInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = passwordInput,
            onValueChange = { passwordInput = it },
            label = { Text(text = "Password") },
            maxLines = 1
        )

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text(text = "Encrypted Text") },
                modifier = Modifier.fillMaxHeight(0.5f)
                    .fillMaxWidth(0.5f)
            )
            Spacer(Modifier.width(10.dp))
            OutlinedTextField(
                value = textDecrypted,
                onValueChange = { textDecrypted = it },
                label = { Text(text = "Decrypted text") },
                modifier = Modifier.fillMaxHeight(0.5f)
                    .fillMaxWidth(1f)
            )
        }

        Button(onClick = {
            textDecrypted = try {
                AesAlgorithm.decrypt(
                    key = passwordInput,
                    cipher = textInput,
                    AesMode.CTR
                )
            } catch (e: Exception) {
                e.localizedMessage
            }
        }) {
            Text("Decrypt")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            App()
        }
    }
}
