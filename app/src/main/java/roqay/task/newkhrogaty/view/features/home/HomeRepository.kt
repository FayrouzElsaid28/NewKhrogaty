package roqay.task.newkhrogaty.view.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import roqay.task.khrogaty.models.activity.Activity
import roqay.task.khrogaty.models.place.Place
import roqay.task.khrogaty.models.restaurant.Restaurant
import roqay.task.newkhrogaty.api.home.HomeFactory
import roqay.task.newkhrogaty.base.helpers.Resource
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category

object HomeRepository {

    fun getAllPlaces(): LiveData<Resource<String>>{
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        HomeFactory.getAllPlaces()
            .enqueue(object : Callback<ArrayList<Place>>{
                override fun onFailure(call: Call<ArrayList<Place>>, t: Throwable) {
                    data.postValue(Resource.error(t.message))
                }

                override fun onResponse(call: Call<ArrayList<Place>>, response: Response<ArrayList<Place>>) {
                    if (response.isSuccessful){
                        if (response.body()?.size!! > 0) {
                            data.postValue(Resource.success(response.message()))
                            Category.placesList = response.body()!!
                        }else{
                            data.postValue(Resource.empty("empty",response.message()))
                        }
                    }else{
                        data.postValue(Resource.error(response.message()))
                    }
                }

            })

        return data
    }

    fun getAllActivities(): LiveData<Resource<String>>{
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        HomeFactory.getAllActivities()
            .enqueue(object : Callback<ArrayList<Activity>>{
                override fun onFailure(call: Call<ArrayList<Activity>>, t: Throwable) {
                    data.postValue(Resource.error(t.message))
                }

                override fun onResponse(call: Call<ArrayList<Activity>>, response: Response<ArrayList<Activity>>) {
                    if (response.isSuccessful){
                        if (response.body()?.size!! > 0) {
                            data.postValue(Resource.success(response.message()))
                            Category.activitiesList = response.body()!!
                        }else{
                            data.postValue(Resource.empty("empty",response.message()))
                        }
                    }else{
                        data.postValue(Resource.error(response.message()))
                    }
                }

            })

        return data
    }

    fun getAllRestaurants(): LiveData<Resource<String>>{
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        HomeFactory.getAllRestaurants()
            .enqueue(object : Callback<ArrayList<Restaurant>>{
                override fun onFailure(call: Call<ArrayList<Restaurant>>, t: Throwable) {
                    data.postValue(Resource.error(t.message))
                }

                override fun onResponse(call: Call<ArrayList<Restaurant>>, response: Response<ArrayList<Restaurant>>) {
                    if (response.isSuccessful){
                        if (response.body()?.size!! > 0) {
                            data.postValue(Resource.success(response.message()))
                            Category.restaurantsList = response.body()!!
                        }else{
                            data.postValue(Resource.empty("empty",response.message()))
                        }
                    }else{
                        data.postValue(Resource.error(response.message()))
                    }
                }

            })

        return data
    }
}