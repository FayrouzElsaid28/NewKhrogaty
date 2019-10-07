package roqay.task.newkhrogaty.api.details

import retrofit2.Call
import roqay.task.khrogaty.models.comment.Comment
import roqay.task.newkhrogaty.api.ServiceBuilder

object DetailsFactory {

    fun getAllComments(post_id: Int): Call<ArrayList<Comment>>{
        val detailsService = ServiceBuilder.buildService(DetailsService::class.java)
        return detailsService.getAllComments(post_id)
    }

    fun addComment(
        author_name:String,
        author_email:String,
        content:String,
        post:Int
    ): Call<Comment>{
        val detailsService = ServiceBuilder.buildService(DetailsService::class.java)
        return detailsService.addComment(
            author_name,
            author_email,
            content,
            post
        )
    }
}