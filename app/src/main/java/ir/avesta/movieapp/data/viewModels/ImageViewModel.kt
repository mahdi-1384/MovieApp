package ir.avesta.movieapp.data.viewModels

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.avesta.movieapp.ui.activities.MainActivity

class ImageViewModel : ViewModel() {
    private val _imageUrl = MutableLiveData<String?>()
    val imageUrl: LiveData<String?>
        get() = _imageUrl

    fun getImage(intent: Intent) {
        _imageUrl.value = intent.getStringExtra(MainActivity.image)
    }
}



class ImageViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageViewModel() as T
    }
}