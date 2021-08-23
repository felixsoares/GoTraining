package com.felix.gotraining.data.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.NumberPicker
import android.widget.TextView
import com.felix.gotraining.R

object ViewDialog {
    fun showSelectDialog(context: Context, select: (range: Int, repeat: Int) -> Unit) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.select_range_repeat_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val rangeNumberPicker = dialog.findViewById<NumberPicker>(R.id.number_picker_range)
        rangeNumberPicker.maxValue = 100
        rangeNumberPicker.minValue = 1

        val repeatNumberPicker = dialog.findViewById<NumberPicker>(R.id.number_picker_repeat)
        repeatNumberPicker.maxValue = 100
        repeatNumberPicker.minValue = 1

        dialog.findViewById<TextView>(R.id.btn_select).setOnClickListener {
            select.invoke(rangeNumberPicker.value, repeatNumberPicker.value)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun showWeightDialog(context: Context, select: (weight: Int) -> Unit) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.select_weight_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val rangeNumberPicker = dialog.findViewById<NumberPicker>(R.id.number_picker_weight)
        rangeNumberPicker.maxValue = 100
        rangeNumberPicker.minValue = 1

        dialog.findViewById<TextView>(R.id.btn_add).setOnClickListener {
            select.invoke(rangeNumberPicker.value)
            dialog.dismiss()
        }
        dialog.show()
    }
}