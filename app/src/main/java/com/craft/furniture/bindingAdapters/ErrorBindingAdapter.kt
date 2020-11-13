package com.craft.furniture.bindingAdapters

import android.widget.EditText
import androidx.databinding.BindingAdapter

/**
 * Binding adapter to show validation error on [EditText]
 * @param errorResource String Resource ID
 */
@BindingAdapter(value = ["errorText"])
 fun setErrorOnEditText(editText: EditText, errorResource :Int?){
    errorResource?.let {
        editText.error = editText.context.resources.getString(errorResource)
    }
}