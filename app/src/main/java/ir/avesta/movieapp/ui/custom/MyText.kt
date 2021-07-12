package ir.avesta.movieapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.avesta.movieapp.R
import ir.avesta.movieapp.databinding.ListlayoutViewholderBinding
import ir.avesta.movieapp.databinding.TextLayoutBinding
import ir.avesta.movieapp.databinding.TextLayoutViewholderBinding
import ir.avesta.movieapp.util.helpers.StringHelper

class MyText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0

) : LinearLayout(context, attrs, defStyle) {

    private var recycler: RecyclerView
    private var content: String? = null

    init {
        View.inflate(context, R.layout.text_layout, this)
        recycler = findViewById(R.id.recycler)
    }

    fun setContent(content: String?) {
        this.content = content

        val list = StringHelper.stringToList(content)
        recycler.adapter = Adapter(list)
    }

    inner class Adapter(
        var list: List<String?>?
    ) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        inner class ViewHolder(private val binding: TextLayoutViewholderBinding)
            : RecyclerView.ViewHolder(binding.root) {
            val tv = binding.tv
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
            return ViewHolder(TextLayoutViewholderBinding
                .inflate(LayoutInflater.from(context), parent, false))
        }

        override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
            val item = list!![position]

            holder.tv.text = item
        }

        override fun getItemCount(): Int = list?.size ?: 0
    }
}