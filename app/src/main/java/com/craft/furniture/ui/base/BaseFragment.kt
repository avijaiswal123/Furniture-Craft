package com.craft.furniture.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.craft.furniture.ui.progressDialog.ProgressFragment
import com.craft.furniture.utils.VMFactory
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Abstract Activity which binds [ViewModel] [VM] and [ViewDataBinding] [VB]
 */
abstract class BaseFragment<VM: ViewModel, VB: ViewDataBinding> : Fragment() {

    @Inject
    lateinit var vmFactory :VMFactory

    private  lateinit var mViewModel :VM

    private lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewModel()

    }
    /**
     * show [ProgressFragment] as progress dialog while doing network request
     */
    fun showProgress(){
        ProgressFragment.showProgress(childFragmentManager)
    }
    /**
     * Hide [ProgressFragment]
     */
    fun hideProgress(){
        ProgressFragment.hideProgressBar()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return performDataBinding(inflater,container)
    }
    /**
     * Override for initializing repository
     *
     * @return repository attached to class
     */
    abstract fun getRepository():Any
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

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?):View {
        mViewBinding = DataBindingUtil.inflate(inflater,
            getLayoutId(),
            container,
            false)

        return with(mViewBinding) {
            setVariable(getBindingVariable(),mViewModel)
            lifecycleOwner = this@BaseFragment
            executePendingBindings()
            root
        }

    }
    /**
     * initialize [ViewModel] and assigned to [mViewModel]
     */
    private fun initializeViewModel(){
        mViewModel = ViewModelProvider(this,
            vmFactory).get(getViewModelClass()
        )

    }
    @Suppress("UNCHECKED_CAST")
    // index of 0 means first argument of BaseActivity class param
    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

}