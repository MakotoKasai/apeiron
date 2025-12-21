package apeiron.mobile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteDetailScreen(
    noteId: String,
    container: AppContainer
) {
    val vm: NoteDetailViewModel = viewModel(
        factory = NoteDetailViewModel.Factory(noteId, container.paragraphUseCase)
    )

    val items by vm.items.collectAsState()
    var text by remember { mutableStateOf("") }

    LaunchedEffect(noteId) { vm.refresh() }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Note: $noteId", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items, key = { it.id }) { p ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Text(p.text)
                        Spacer(Modifier.height(8.dp))
                        Row {
                            Spacer(Modifier.weight(1f))
                            TextButton(onClick = { vm.delete(p.id) }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("New paragraph") }
        )

        Spacer(Modifier.height(8.dp))

        Row(Modifier.fillMaxWidth()) {
            Button(onClick = {
                vm.add(text)
                text = ""
            }) {
                Text("Add")
            }
            Spacer(Modifier.width(12.dp))
            OutlinedButton(onClick = { vm.refresh() }) {
                Text("Refresh")
            }
        }
    }
}