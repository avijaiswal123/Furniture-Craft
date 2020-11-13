package com.craft.furniture.ui.progressDialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.craft.furniture.R

/**
 * [DialogFragment] to show progress while doing Network Request
 */
class ProgressFragment : DialogFragment() {
    companion object {
        private const val FRAGMENT_TAG = "ProgressBar"
        private var newInstance = ProgressFragment()

        /**
         * To show Dialog
         */
        fun showProgress(supportFragmentManager: FragmentManager) {
            hideProgressBar()
            try {
                supportFragmentManager.executePendingTransactions()

                if (newInstance.isVisible or newInstance.isAdded or newInstance.isInLayout) {
                    return
                }
                newInstance.apply {
                    isCancelable = false
                    showNow(supportFragmentManager, FRAGMENT_TAG)
                }
            }catch (e: Exception){}
        }

        /**
         * To hide Dialog
         */
        fun hideProgressBar() {
            try {
                if(newInstance.isVisible or newInstance.isAdded or newInstance.isInLayout){
                    newInstance.dismissAllowingStateLoss()
                }
            }catch (e:Exception){ newInstance.dismiss()}

        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.progress_dialog_fragment, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
        }
       return dialog
    }

    override fun onStart() {
        super.onStart()

        // remove black outer overlay, or change opacity
        dialog?.window?.also { window ->
            window.attributes?.also { attributes ->
                attributes.dimAmount = 0.6f
                window.attributes = attributes
            }
        }
    }

}
