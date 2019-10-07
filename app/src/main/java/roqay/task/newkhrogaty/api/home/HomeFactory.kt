package roqay.task.newkhrogaty.api.home

import retrofit2.Call
import roqay.task.khrogaty.models.activity.Activity
import roqay.task.khrogaty.models.place.Place
import roqay.task.khrogaty.models.restaurant.Restaurant
import roqay.task.newkhrogaty.api.ServiceBuilder

object HomeFactory {

    fun getAllPlaces(): Call<ArrayList<Place>>{
        val destinationService = ServiceBuilder.buildService(HomeService::class.java)
        return destinationService.getAllPlaces(4)
    }

    fun getAllActivities(): Call<ArrayList<Activity>>{
        val destinationService = ServiceBuilder.buildService(HomeService::class.java)
        return destinationService.getAllActivities(2)
    }

    fun getAllRestaurants(): Call<ArrayList<Restaurant>>{
        val destinationService = ServiceBuilder.buildService(HomeService::class.java)
        return destinationService.getAllRestaurants(3)
    }
}