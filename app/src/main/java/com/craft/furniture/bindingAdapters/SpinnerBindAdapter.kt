package com.craft.furniture.bindingAdapters

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
/**
 * Binding adapter to set list of items on [Spinner]
 * @param list List of Items
 * */
@BindingAdapter(value = ["entries"])
fun setEntries(spinner: Spinner,list:List<String>?){
    list?.let {
        spinner.adapter = ArrayAdapter(spinner.context,android.R.layout.simple_dropdown_item_1line,it)

    }
}

/**
 * @BindingAdapter to set item on [Spinner] after click
 * @param oldValue previous selected item
 * @param newValue current clicked item
 * */
@BindingAdapter(value = ["onItemSelected"] )
fun setOnItemSelected(spinner:Spinner,
                      oldValue:String?,
                      newValue:String?,
) {
    //Break the infinite loop
   if(oldValue != newValue){
       spinner.setSelection(spinner.selectedItemPosition)
   }

}
/**
 * @InverseBindingAdapter to get the selected item on [Spinner]
 *
 * */
@InverseBindingAdapter(attribute = "onItemSelected", event = "onItemSelectedAttrChanged")
fun getOnItemSelected(spinner: Spinner):String?{
    return spinner.adapter.getItem(spinner.selectedItemPosition) as? String?
}

/**
 * @InverseBindingListener on [Spinner]
 *
 * */
@BindingAdapter("onItemSelectedAttrChanged")
fun setItemSelectedAttrChangeListener(spinner: Spinner,attrChanged :InverseBindingListener?){
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
            attrChanged?.onChange()
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }
}