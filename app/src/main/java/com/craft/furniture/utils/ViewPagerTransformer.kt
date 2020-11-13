package com.craft.furniture.utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

/**
 * Custom [ViewPager2.PageTransformer] class to animate [ViewPager2]
 *
 */
class ViewPagerTransformer: ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        setVerticalBookLikeAnimation(page, position)
    }
    private fun setVerticalBookLikeAnimation(view: View, position: Float) {
        val pageHeight = view.height
        when {
            position < -1 -> { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 0f
            }
            position < 0 -> { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.apply {
                    alpha = max(
                        MIN_ALPHA,
                        1 - abs(position)
                    )
                    translationX = 0f
                    translationZ = 0f
                    translationY = 0f
                    scaleX = 1f
                    scaleY = 1f
                }

            }
            position == 0f -> {
                view.apply {
                    alpha = 1f
                    translationX = 0f
                    translationZ = 0f
                    translationY = 0f
                    scaleX = 1f
                    scaleY = 1f
                }

            }
            position < 1 -> { // (0,1]
                view.apply {
                    // Fade the page out.
                    alpha = 1f

                    // Counteract the default slide transition
                    translationX = 0f
                    // Move it behind the left page
                    translationZ = -1f
                    translationY = pageHeight * -position

                    // Scale the page down (between MIN_SCALE and 1)
                    val scaleFactor: Float =
                        (MIN_SCALE
                                + (1 - MIN_SCALE) * (1 - abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }

            }
            else -> { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 0f
            }
        }
    }
    companion object{
        private const val MIN_SCALE = 0.85f
        private const val MIN_ALPHA = 1f
    }
}