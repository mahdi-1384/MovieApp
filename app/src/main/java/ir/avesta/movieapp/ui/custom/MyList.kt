package ir.avesta.movieapp.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.avesta.movieapp.R
import ir.avesta.movieapp.data.remote.model.Rating
import ir.avesta.movieapp.databinding.ListlayoutViewholderBinding
import ir.avesta.movieapp.util.beGone
import ir.avesta.movieapp.util.beVisible
import kotlin.concurrent.fixedRateTimer

@SuppressLint("ResourceType", "Recycle")
class MyList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0

) : LinearLayout(context, attrs, defStyle) {

    private var recycler: RecyclerView
    private var top: ViewGroup
    private var arrowImg: ImageView

    private var list: List<Rating?>? = null

    init {
        View.inflate(context, R.layout.list_layout, this)
        recycler = findViewById(R.id.recycler)
        top = findViewById(R.id.top)
        arrowImg  = findViewById(R.id.arrowImg)

        if (list?.size == 0) {
            arrowImg.beGone()
            top.isClickable = false
        }

        top.setOnClickListener {
            val newState = rotateArrow()
            if (newState == State.open) {
                recycler.adapter = Adapter(list)
                recycler.beVisible()

            } else {
                recycler.beGone()
            }
        }
    }

    fun setList(list: List<Rating?>?) {
        this.list = list

        if (isArrowOpen(arrowImg) == State.open)
            recycler.adapter = Adapter(list)
    }

    fun rotateArrow(): State {
        val currentRotation = arrowImg.rotation
        val newRotation = if (currentRotation == -90f) 90f else -90f

        arrowImg.animate()
            .rotation(newRotation)
            .start()

        return if (isArrowOpen(arrowImg) == State.open) State.closed else State.open
    }

    fun isArrowOpen(arrow: ImageView) = if (arrow.rotation == -90f) State.closed else State.open

    inner class Adapter(
        private var list: List<Rating?>?

    ) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        inner class ViewHolder(private val binding: ListlayoutViewholderBinding)
            : RecyclerView.ViewHolder(binding.root) {
            val first = binding.first
            val sourceTv = binding.sourceTv
            val valueTv = binding.valueTv
            val bottomLine = binding.bottomLine
            val topLine = binding.topLine
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
            return ViewHolder(ListlayoutViewholderBinding
                .inflate(LayoutInflater.from(context), parent, false))
        }

        override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
            val rating = list!![position]

            holder.sourceTv.text = rating?.Source
            holder.valueTv.text = rating?.Value

            holder.first.visibility = if (position == 0) VISIBLE else GONE

            if (position == list!!.size - 1)
                holder.bottomLine.beVisible()
            else
                holder.bottomLine.beGone()

            if (position == 0)
                holder.topLine.beVisible()
            else
                holder.topLine.beGone()
        }

        override fun getItemCount(): Int = list?.size ?: 0
    }

    enum class State {
        open,
        closed
    }
}