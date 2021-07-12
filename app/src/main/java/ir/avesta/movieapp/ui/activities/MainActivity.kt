package ir.avesta.movieapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import ir.avesta.movieapp.MyApplication
import ir.avesta.movieapp.R
import ir.avesta.movieapp.data.viewModels.MainViewModel
import ir.avesta.movieapp.data.viewModels.MainViewModelFactory
import ir.avesta.movieapp.databinding.ActivityMainBinding
import ir.avesta.movieapp.ui.fragments.MovieFragment
import ir.avesta.movieapp.util.State
import ir.avesta.movieapp.util.beGone
import ir.avesta.movieapp.util.beVisible
import ir.avesta.movieapp.util.mVisibility
import kotlinx.coroutines.processNextEventInCurrentThread

class MainActivity : AppCompatActivity(), TextWatcher {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MyApplication).repository)
    }

    companion object {
        const val image = "ir.avesta.movieapp.ui.activities.MainActivity.image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        binding.movieNameEdt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getMovieByTitle(binding.movieNameEdt.text)

                true
            }

            false
        }

        //listeners and observers
        binding.movieNameEdt.addTextChangedListener(this)
        viewModel.state.observe(this, loadingChanged)
    }

    fun refresh(v: View) = viewModel.getMovieByTitle(binding.movieNameEdt.text)

    private val loadingChanged = Observer<State> {
        when(it) {

            State.noContent -> {
                binding.progressbar.beGone()
                binding.errorInc.root.beGone()
                binding.frag.beGone()
                binding.movieNotExistInc.root.beGone()
                binding.noContentImg.beVisible()
            }

            State.error -> {
                binding.progressbar.beGone()
                binding.frag.beGone()
                binding.errorInc.root.beVisible()
                binding.movieNotExistInc.root.beGone()
                binding.noContentImg.beGone()
            }

            State.loadingStart -> {
                binding.frag.beGone()
                binding.errorInc.root.beGone()
                binding.progressbar.beVisible()
                binding.movieNotExistInc.root.beGone()
                binding.noContentImg.beGone()
            }

            State.loadingFinished -> {
                binding.frag.beVisible()
                binding.errorInc.root.beGone()
                binding.progressbar.beGone()
                binding.movieNotExistInc.root.beGone()
                binding.noContentImg.beGone()
            }

            State.movieNotExist -> {
                binding.frag.beGone()
                binding.errorInc.root.beGone()
                binding.progressbar.beGone()
                binding.movieNotExistInc.root.beVisible()
                binding.noContentImg.beGone()
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        viewModel.state.value = State.noContent
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    fun displayImage(v: View) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(image, viewModel.poster.value)
        startActivity(intent)
    }



    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initFragment()
        initImage()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frag, MovieFragment.newInstance())
            .commit()
    }

    private fun initImage() {
        Handler(Looper.getMainLooper()).postDelayed(kotlinx.coroutines.Runnable {
            binding.noContentImg.animate().scaleX(1.4f)
                .scaleY(1.4f)
                .start()
        }, 400)
    }
}