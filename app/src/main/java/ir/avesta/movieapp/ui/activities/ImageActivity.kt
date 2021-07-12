package ir.avesta.movieapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import ir.avesta.movieapp.R
import ir.avesta.movieapp.data.viewModels.ImageViewModel
import ir.avesta.movieapp.data.viewModels.ImageViewModelFactory
import ir.avesta.movieapp.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding
    private val viewModel: ImageViewModel by viewModels {
        ImageViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        viewModel.getImage(intent)
    }


    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}