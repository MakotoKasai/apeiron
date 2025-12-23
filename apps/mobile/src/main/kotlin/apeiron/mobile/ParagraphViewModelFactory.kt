package apeiron.mobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import apeiron.core.paragraph.ParagraphRepository

class ParagraphViewModelFactory(
    private val repository: ParagraphRepository
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParagraphViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ParagraphViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}