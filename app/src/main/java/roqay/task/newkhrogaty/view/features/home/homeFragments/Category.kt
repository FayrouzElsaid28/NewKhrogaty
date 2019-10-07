package roqay.task.newkhrogaty.view.features.home.homeFragments

import roqay.task.khrogaty.models.activity.Activity
import roqay.task.khrogaty.models.place.Place
import roqay.task.khrogaty.models.restaurant.Restaurant
import roqay.task.newkhrogaty.view.features.home.homeFragments.search.Search

object Category {
    var details_name: String = ""
    var details_img_url: String = ""
    var details_information: String = ""
    var details_place: String = ""
    var details_phone: String = ""
    var details_email: String = ""
    var details_map_location_url: String = ""
    var post_id: Int = -1

    var placesList = arrayListOf<Place>()
    var activitiesList = arrayListOf<Activity>()
    var restaurantsList = arrayListOf<Restaurant>()
    var searchList = arrayListOf<Search>()
}