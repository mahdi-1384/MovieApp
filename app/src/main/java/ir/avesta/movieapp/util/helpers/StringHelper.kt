package ir.avesta.movieapp.util.helpers

import android.util.Log

class StringHelper {
    companion object {
        fun stringToList(str: String?): List<String?>? {
            val result: MutableList<String?>? = mutableListOf()
            val string = "$str,"

            var lastIndex = 0
            for (i in 0 until string.length) {
                val chosen = string.substring(i, i + 1)

                if (chosen.equals(",")) {
                    val toAdd = string.substring(lastIndex, i)
                    result?.add(toAdd)
                    lastIndex = i + 1
                }
            }

            Log.d("myAppLog","list is : ${result}")
            return result
        }
    }
}