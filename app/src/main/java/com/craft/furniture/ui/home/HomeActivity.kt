package com.craft.furniture.ui.home

import android.os.Bundle
import com.craft.furniture.BR
import com.craft.furniture.R
import com.craft.furniture.data.model.db.FurnitureListEntity
import com.craft.furniture.databinding.ActivityHomeBinding
import com.craft.furniture.ui.base.BaseActivity
import com.craft.furniture.utils.*


class HomeActivity : BaseActivity <HomeViewModel, ActivityHomeBinding>() {

    private val mHomeBinding by lazy { getBinding() }
    private val mHomeViewModel by lazy { getViewModel() }
    private val homeAdapter by lazy { HomeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewPager()
        observeNetworkChange()
        observeHomeData()
    }


    private fun observeHomeData() {
        mHomeViewModel.homeData.observe(this, {
            when (it) {
                is Result.Loading -> showProgress()
                is Result.Success -> onSuccess(it.data)
                is Result.Error -> showErrorMsg(it.msgOrId)
            }
        })
    }

    private fun onSuccess(data: List<FurnitureListEntity>?) {
        hideProgress()
        data?.let {
            homeAdapter.setData(it)
        }
    }

    private fun setViewPager() {
        mHomeBinding.furniturePager.apply {
            adapter = homeAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 5
            setPageTransformer(ViewPagerTransformer())
        }
    }

    private fun showErrorMsg(msgOrId: Any) {
        hideProgress()
        when (msgOrId) {
            is Int -> showToast(getResString(msgOrId))
            is String -> showToast(msgOrId)
        }
    }

    private fun observeNetworkChange() {
        networkLiveData.observe(this, { isOnline ->
            when (isOnline) {
                true -> fetchNewList()
                else ->showInActiveStatus()
            }
        })
    }



    private fun fetchNewList() {
        if (mHomeViewModel.homeData.value is Result.Error || homeAdapter.itemCount == 0) {
            mHomeViewModel.getHomePageData()
        }
        showActiveStatus()
    }
    private fun showActiveStatus() {
        mHomeBinding.root.showSnackBar(getResString(R.string.active_network_msg))
    }

    private fun showInActiveStatus() {
        mHomeBinding.root.showSnackBar(getResString(R.string.in_active_network_msg))
    }


    override fun getBindingVariable() = BR.homeVM

    override fun getLayoutId() = R.layout.activity_home
}