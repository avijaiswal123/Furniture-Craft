package com.craft.furniture.ui.home

import androidx.lifecycle.*
import com.craft.furniture.data.model.db.FurnitureListEntity
import com.craft.furniture.utils.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeData = MutableLiveData<Result<List<FurnitureListEntity>>>()
    val homeData: LiveData<Result<List<FurnitureListEntity>>> = _homeData

//    val furnitureList:LiveData<List<FurnitureListEntity>> =
//        Transformations.map(homeData){
//            when(it){
//                is Result.Success->it.data
//                else -> null
//            }
//        }

    init {
        getHomePageData()
    }

     fun getHomePageData(){
        viewModelScope.launch {
            homeRepository.getHomeData().collect {
                _homeData.value = it
            }
        }
    }
}