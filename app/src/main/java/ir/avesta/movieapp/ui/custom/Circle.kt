package ir.avesta.movieapp.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class Circle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0

) : View(context, attrs, defStyle) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        Log.d("myAppLog","width is : ${this.width}")

        canvas?.drawCircle(this.width.toFloat() / 2,this.height.toFloat() / 2,this.width.toFloat() / 2, Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = Color.BLACK
        })
    }

}