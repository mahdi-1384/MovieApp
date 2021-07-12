package ir.avesta.movieapp.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import ir.avesta.movieapp.R
import ir.avesta.movieapp.data.remote.model.Rating
import ir.avesta.movieapp.ui.custom.MyList
import ir.avesta.movieapp.ui.custom.MyText

@BindingAdapter("mVisibility")
fun View.mVisibility(visible: Boolean) {
    this.visibility = if (visible) VISIBLE else GONE
}

@BindingAdapter("url")
fun ImageView.url(url: String?) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.not_existing)
        .into(this)
}

@BindingAdapter("list")
fun MyList.list(list: List<Rating>?) {
    this.setList(list)
}

@BindingAdapter("content")
fun MyText.content(content: String?) {
    this.setContent(content)
}