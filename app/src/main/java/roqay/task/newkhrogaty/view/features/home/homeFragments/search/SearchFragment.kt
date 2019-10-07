package roqay.task.newkhrogaty.view.features.home.homeFragments.search


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*

import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.AdapterToViewCallBack
import roqay.task.newkhrogaty.base.extensions.makeLongToast
import roqay.task.newkhrogaty.base.extensions.openActivtyFromParent
import roqay.task.newkhrogaty.view.features.details.DetailsActivity
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.home.homeFragments.ICategory

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(),
    AdapterToViewCallBack,
    ICategory {

    private val searchAdapter = SearchAdapter(this,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nosearch_icon.setOnClickListener {
            if (search_et.text.trim().isNotEmpty())
                search(search_et.text.toString())
            else
                makeLongToast("Please type something")
        }

        search_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (search_et.text.trim().length > 1)
                    clearPastSearch()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun search(search_txt: String) {
        searchInPlaces(search_txt)
        searchInActivities(search_txt)
        searchinRestaurants(search_txt)

        initView()
    }

    private fun initView() {
        search_et.setText("")

        search_recyclerview.layoutManager = LinearLayoutManager(context)
        search_recyclerview.adapter= searchAdapter
        loadSearchResult()
    }

    private fun clearPastSearch(){
        Category.searchList.clear()

        nosearch_icon.visibility = View.VISIBLE
        search_result_tv.visibility = View.VISIBLE
        empty.visibility = View.GONE
        search_recyclerview.visibility = View.GONE
    }

    private fun loadSearchResult() {

        nosearch_icon.visibility = View.GONE
        search_result_tv.visibility = View.GONE

        when(Category.searchList.size){
            0 -> {
                empty.visibility = View.VISIBLE
                search_recyclerview.visibility = View.GONE
            }
            else -> {
                searchAdapter.setData(Category.searchList)
                empty.visibility = View.GONE
                search_recyclerview.visibility = View.VISIBLE
            }
        }
    }

    private fun searchinRestaurants(search_txt: String) {
        for (restaurant in Category.restaurantsList){
            if (restaurant.title.rendered.contains(search_txt,true)) {
                val searchItem = Search()
                setSearchData(
                    searchItem, restaurant.title.rendered,
                    restaurant.acf.email_address,
                    restaurant.better_featured_image.source_url,
                    restaurant.excerpt.rendered,
                    restaurant.acf.map_location,
                    restaurant.acf.phone_number,
                    restaurant.acf.address,
                    restaurant.better_featured_image.post
                )
                Category.searchList.add(searchItem)
            }
        }
    }

    private fun searchInActivities(search_txt: String) {
        for (activity in Category.activitiesList){
            if (activity.title.rendered.contains(search_txt,true)) {
                val searchItem = Search()
                setSearchData(
                    searchItem, activity.title.rendered,
                    activity.acf.email_address,
                    activity.better_featured_image.source_url,
                    activity.excerpt.rendered,
                    activity.acf.map_location,
                    activity.acf.phone_number,
                    activity.acf.address,
                    activity.better_featured_image.post
                )
                Category.searchList.add(searchItem)
            }
        }
    }

    private fun searchInPlaces(search_txt: String) {
        for (place in Category.placesList){
            if (place.title.rendered.contains(search_txt,true)) {
                val searchItem = Search()
                setSearchData(
                    searchItem,
                    place.title.rendered,
                    place.acf.email_address,
                    place.better_featured_image.source_url,
                    place.excerpt.rendered,
                    place.acf.map_location,
                    place.acf.phone_number,
                    place.acf.address,
                    place.better_featured_image.post
                )
                Category.searchList.add(searchItem)
            }
        }

        Log.d("size",Category.searchList.size.toString())
    }

    private fun setSearchData(
        searchItem: Search,
        name: String,
        emailAddress: String,
        img_uri: String,
        information: String,
        mapLocation: String,
        phoneNumber: String,
        address: String,
        post: Int
    ) {
        searchItem.search_name = name
        searchItem.search_email = emailAddress
        searchItem.search_img_url = img_uri
        searchItem.search_information = information
        searchItem.search_map_location_url = mapLocation
        searchItem.search_phone = phoneNumber
        searchItem.search_place = address
        searchItem.search_post_id = post
    }

    override fun getContext(): Context {
        return activity?.applicationContext!!
    }

    override fun openActivity() {
        openActivtyFromParent(DetailsActivity::class.java)
    }


}
