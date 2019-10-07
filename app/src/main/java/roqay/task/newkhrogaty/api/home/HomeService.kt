package roqay.task.newkhrogaty.api.home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import roqay.task.khrogaty.models.activity.Activity
import roqay.task.khrogaty.models.place.Place
import roqay.task.khrogaty.models.restaurant.Restaurant

interface HomeService {

    @GET("posts")
    fun getAllPlaces(
        @Query("categories") categories: Int
    ): Call<ArrayList<Place>>

    @GET("posts")
    fun getAllActivities(
        @Query("categories") categories: Int
    ): Call<ArrayList<Activity>>

    @GET("posts")
    fun getAllRestaurants(
        @Query("categories") categories: Int
    ): Call<ArrayList<Restaurant>>
}