package roqay.task.newkhrogaty.view.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roqay.task.newkhrogaty.base.helpers.Resource
import roqay.task.newkhrogaty.view.features.home.HomeRepository

class HomeViewModel: ViewModel() {
    fun getAllPlaces(): LiveData<Resource<String>>{
        return HomeRepository.getAllPlaces()
    }

    fun getAllActivities(): LiveData<Resource<String>>{
        return HomeRepository.getAllActivities()
    }

    fun getAllRestaurants(): LiveData<Resource<String>>{
        return HomeRepository.getAllRestaurants()
    }
}