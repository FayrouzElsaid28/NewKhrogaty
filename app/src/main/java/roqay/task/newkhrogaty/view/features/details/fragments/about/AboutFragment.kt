package roqay.task.newkhrogaty.view.features.details.fragments.about


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_about.*

import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.makeLongToast
import roqay.task.newkhrogaty.base.helpers.Resource
import roqay.task.newkhrogaty.view.features.details.DetailsViewModel
import roqay.task.newkhrogaty.view.features.details.fragments.Details
import roqay.task.newkhrogaty.view.features.details.fragments.IDetails
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category

class AboutFragment : Fragment(), IDetails {

    private val commentAdapter = CommentAdapter()

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getDetails()
    }

    private fun initView() {
        comments_recyclerview.layoutManager = LinearLayoutManager(context)
        comments_recyclerview.adapter = commentAdapter

        send_comment_img.setOnClickListener {
            sendComment()
        }
    }

    private fun sendComment() {
        if (add_comment_et.text.trim().isNotEmpty()){
            viewModel.addComment(
                "Fayrouz",
                "Fayrouzelsaid28@gmail.com",
                add_comment_et.text.toString(),
                Category.post_id)
                .observe(this, Observer {
                    when(it.status){
                        Resource.Status.SUCCESS -> {
                            makeLongToast("Comment added successfully")
                            commentAdapter.notifyDataSetChanged()
                            add_comment_et.setText("")
                            loading.visibility = View.GONE
                        }
                        Resource.Status.LOADING -> {
                            loading.visibility = View.VISIBLE
                        }
                        Resource.Status.EMPTY -> {
                            makeLongToast(it.message!!)
                            loading.visibility = View.GONE
                        }
                        Resource.Status.ERROR -> {
                            makeLongToast(it?.message!!)
                            loading.visibility = View.GONE
                        }
                    }
                })

        }else{
            makeLongToast("Please type something")
        }
    }

    override fun getDetails() {
        val imageUrl = Category.details_img_url
        Picasso.with(context).load(imageUrl).into(details_img)
        details_name.text = Category.details_name
        details_info.text = Category.details_information
        details_place.text = Category.details_place
        details_phone.text = Category.details_phone
        details_mail.text = Category.details_email

        loadComments(Category.post_id)
    }

    private fun loadComments(postId: Int) {
        viewModel.getAllComments(postId)
            .observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        //Update adapter list
                        commentAdapter.setData(Details.commentsList)
                        loading.visibility = View.GONE
                    }
                    Resource.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                    Resource.Status.EMPTY -> {
                        makeLongToast("No comments found")
                        loading.visibility = View.GONE
                    }
                    Resource.Status.ERROR -> {
                        loading.visibility = View.GONE
                        makeLongToast(it.message!!)
                        Log.d("Error", it.message)
                    }

                }
            })
    }

}
