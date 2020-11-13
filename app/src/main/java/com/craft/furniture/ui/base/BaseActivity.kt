package com.craft.furniture.ui.base
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.craft.furniture.ui.progressDialog.ProgressFragment
import com.craft.furniture.utils.NetworkLiveData
import com.craft.furniture.utils.VMFactory
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Abstract Activity which binds [ViewModel] [VM] and [ViewDataBinding] [VB]
 */
abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding>: AppCompatActivity() {
    @Inject
    lateinit var vmFactory :VMFactory

    @Inject
    lateinit var networkLiveData : NetworkLiveData

    private  lateinit var mViewModel :VM

    private lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initializeViewModel()
        performDataBinding()

    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * Override for  resource id
     *
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * @return [ViewModel]
     */
    open fun getViewModel() = mViewModel

    /**
     * @return data binding
     */
    open fun getBinding() = mViewBinding

    private fun performDataBinding() {
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewBinding.apply {
           setVariable(getBindingVariable(), mViewModel)
           lifecycleOwner = this@BaseActivity
           executePendingBindings()
        }
    }

    private fun initializeViewModel() {
        mViewModel = ViewModelProvider(this,vmFactory).get(getViewModelClass())
    }
    /**
     * show [ProgressFragment] as progress dialog while doing network request
     */
    fun showProgress(){
        ProgressFragment.showProgress(supportFragmentManager)
    }

    /**
     * Hide [ProgressFragment]
     */
    fun hideProgress(){
        ProgressFragment.hideProgressBar()
    }


    /**
     * Convert Generic class to typed class for [ViewModel].
     * Index of 0 means first argument of BaseActivity class param
    */
    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

}