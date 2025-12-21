package apeiron.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    private lateinit var container: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        container = AppContainer(applicationContext)

        setContent {
            // まず固定IDでOK。後で Note 一覧画面を作って遷移する
            NoteDetailScreen(noteId = "note-001", container = container)
        }
    }
}