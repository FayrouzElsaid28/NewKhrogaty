package roqay.task.newkhrogaty.view.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import roqay.task.newkhrogaty.base.helpers.Resource

class DetailsViewModel: ViewModel() {
    fun getAllComments(post_id: Int): LiveData<Resource<String>>{
        return DetailsRepository.getAllComments(post_id)
    }

    fun addComment(
        author_name:String,
        author_email:String,
        content:String,
        post:Int
    ): LiveData<Resource<String>>{
        return DetailsRepository.addComment(
            author_name,
            author_email,
            content,
            post
        )
    }
}