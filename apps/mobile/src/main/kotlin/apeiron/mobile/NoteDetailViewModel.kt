package apeiron.mobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import apeiron.core.paragraph.Paragraph
import apeiron.core.paragraph.ParagraphUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class NoteDetailViewModel(
    private val noteId: String,
    private val useCase: ParagraphUseCase
) : ViewModel() {

    private val _items = MutableStateFlow<List<Paragraph>>(emptyList())
    val items: StateFlow<List<Paragraph>> = _items

    fun refresh() {
        viewModelScope.launch {
            _items.value = useCase.list(noteId)
        }
    }

    fun add(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return

        viewModelScope.launch {
            useCase.add(noteId, UUID.randomUUID().toString(), trimmed)
            refresh()
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            useCase.delete(id)
            refresh()
        }
    }

    class Factory(
        private val noteId: String,
        private val useCase: ParagraphUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NoteDetailViewModel(noteId, useCase) as T
        }
    }
}
