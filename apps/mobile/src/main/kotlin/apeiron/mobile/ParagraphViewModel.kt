package apeiron.mobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apeiron.core.paragraph.Paragraph
import apeiron.core.paragraph.ParagraphRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.collections.emptyList
import kotlinx.datetime.Clock

class ParagraphViewModel(
    private val repository: ParagraphRepository
): ViewModel() {
    private val _items = MutableStateFlow<List<Paragraph>>(emptyList())
    val items: StateFlow<List<Paragraph>> = _items.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            _items.value = repository.listUnassigned()
        }
    }
    fun add(text: String) {
        val now = Clock.System.now()
        val paragraph = Paragraph(
            id = UUID.randomUUID().toString(),
            noteId = null,
            text = text,
            createdAtMillis = now,
            updatedAtMillis = now
        )
        viewModelScope.launch {
            repository.add(paragraph)
            refresh()
        }
    }
}