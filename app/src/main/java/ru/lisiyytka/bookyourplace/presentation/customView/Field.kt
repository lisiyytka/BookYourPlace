package ru.lisiyytka.bookyourplace.presentation.customView

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import ru.lisiyytka.bookyourplace.R
import ru.lisiyytka.bookyourplace.utils.AppTextWatcher

@SuppressLint("CustomViewStyleable")
class Field @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_field, this)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.field_View)

            val label = typedArray.getString(R.styleable.field_View_label)
            val hint = typedArray.getString(R.styleable.field_View_hint)
            val inputType = typedArray.getString(R.styleable.field_View_input_type)

            val labelField = findViewById<TextView>(R.id.field_name)
            val field = findViewById<EditText>(R.id.field)


            labelField.text = label
            field.hint = hint

            when (inputType) {
                "text" ->
                    field.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                "date" ->
                    field.inputType = InputType.TYPE_CLASS_DATETIME or InputType.TYPE_DATETIME_VARIATION_DATE
                "phone" ->
                    field.inputType = InputType.TYPE_CLASS_PHONE
                "mail" ->
                    field.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                "password" ->
                    field.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                else ->
                    field.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
            }

            field.addTextChangedListener(AppTextWatcher {
                if (field.text.toString().isNotEmpty()) {
                    labelField.visibility = View.VISIBLE
                } else {
                    labelField.visibility = View.GONE
                }
            })

            typedArray.recycle()
        }
    }

    fun getText(): String {
        val field = findViewById<EditText>(R.id.field)
        return field.text.toString()
    }

    fun setText(text: String) {
        val field = findViewById<EditText>(R.id.field)
        field.setText(text)
    }
}