package com.craft.furniture.bindingAdapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.craft.furniture.R
import com.craft.furniture.constants.ApiConstants

/** Load url on [ImageView]
*  @param url Url of image
 * @param errorImage errorDrawable in case of failure in loading.
*
*/
@BindingAdapter(value = ["loadUrl", "errorImage"], requireAll = false)
fun loadImageUrl(imageView: ImageView, url: String?, errorImage: Drawable?) {
    val placeHolder =
        CircularProgressDrawable(imageView.context.applicationContext).apply {
            strokeWidth = 5f
            centerRadius = 30f
            setColorSchemeColors(android.R.color.white)
            start()
        }


    var errorDrawable = errorImage
    errorDrawable?.let {
        errorDrawable = ContextCompat.getDrawable(imageView.context, R.drawable.blurred_background)
    }

    if (!url.isNullOrBlank()) {
        Glide.with(imageView.context.applicationContext)
            .load(ApiConstants.IMAGE_BASE_URL+ url)
            .placeholder(placeHolder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(errorDrawable)
            .into(imageView)

    } else {
        Glide.with(imageView.context.applicationContext).clear(imageView)
        Glide.with(imageView.context).pauseAllRequests()
        imageView.setImageDrawable(errorDrawable)
    }

}
