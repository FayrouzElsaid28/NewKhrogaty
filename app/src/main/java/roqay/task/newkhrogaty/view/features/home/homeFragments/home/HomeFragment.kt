package roqay.task.newkhrogaty.view.features.home.homeFragments.home


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import roqay.task.newkhrogaty.view.features.home.homeFragments.home.Adapters.PlaceAdapter
import roqay.task.newkhrogaty.view.features.home.homeFragments.home.Adapters.RestaurantAdapter
import roqay.task.newkhrogaty.view.features.home.homeFragments.home.Adapters.ToDoAdapter
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.AdapterToViewCallBack
import roqay.task.newkhrogaty.base.extensions.makeLongToast
import roqay.task.newkhrogaty.base.extensions.openActivityFromParent
import roqay.task.newkhrogaty.base.helpers.Resource
import roqay.task.newkhrogaty.view.features.details.DetailsActivity
import roqay.task.newkhrogaty.view.features.home.HomeViewModel
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.home.homeFragments.ICategory
import roqay.task.newkhrogaty.view.features.settings.SettingsActivity

class HomeFragment : Fragment(), AdapterToViewCallBack, ICategory {

    private val placesAdapter = PlaceAdapter(this, this)
    private val restaurantsAdapter = RestaurantAdapter(this,this)
    private val toDoAdapter = ToDoAdapter(this,this)

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
    }

    private fun initViewModel() {
        loadPlaces()
        loadActivities()
        loadRestaurants()
    }

    private fun loadActivities() {
        viewModel.getAllActivities()
            .observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        toDoAdapter.setData(Category.activitiesList)
                        loading.visibility = View.GONE
                    }
                    Resource.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                    Resource.Status.EMPTY -> {
                        loading.visibility = View.GONE
                        makeLongToast("No Activities Found")
                    }
                    Resource.Status.ERROR -> {
                        loading.visibility = View.GONE
                        makeLongToast(it.message!!)
                    }
                }
            })
    }

    private fun loadRestaurants() {
        viewModel.getAllRestaurants()
            .observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        restaurantsAdapter.setData(Category.restaurantsList)
                        loading.visibility = View.GONE
                    }
                    Resource.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                    Resource.Status.EMPTY -> {
                        loading.visibility = View.GONE
                        makeLongToast("No Restaurants Found")
                    }
                    Resource.Status.ERROR -> {
                        loading.visibility = View.GONE
                        makeLongToast(it.message!!)
                    }
                }
            })
    }

    private fun loadPlaces() {
        viewModel.getAllPlaces()
            .observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        placesAdapter.setData(Category.placesList)
                        loading.visibility = View.GONE
                    }
                    Resource.Status.ERROR -> {
                        loading.visibility = View.GONE
                        makeLongToast(it.message!!)
                    }
                    Resource.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                    Resource.Status.EMPTY -> {
                        makeLongToast("No Places Found")
                        loading.visibility = View.GONE
                    }
                }
            })
    }

    private fun initView(){
        //Set horizontal layout manager
        places_for_going_out_recyclerview.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)

        restaurants_recyclerview.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)

        todo_recyclerview.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)


        places_for_going_out_recyclerview.adapter = placesAdapter
        restaurants_recyclerview.adapter = restaurantsAdapter
        todo_recyclerview.adapter = toDoAdapter

        settings_img.setOnClickListener {
            openActivityFromParent(SettingsActivity::class.java)
        }

        places_for_going_out_view_more_tv.setOnClickListener {
            activity?.home_viewpager?.currentItem = 2
        }
        restaurants_view_more_tv.setOnClickListener {
            activity?.home_viewpager?.currentItem = 3
        }
        todo_view_more_tv.setOnClickListener {
            activity?.home_viewpager?.currentItem = 4
        }
    }

    override fun getContext(): Context {
        return activity?.applicationContext!!
    }

    override fun openActivity() {
        openActivityFromParent(DetailsActivity::class.java)
    }

}
