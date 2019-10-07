package roqay.task.newkhrogaty.api.details

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import roqay.task.khrogaty.models.comment.Comment

interface DetailsService {

    @GET("comments")
    fun getAllComments(
        @Query("post") post: Int
    ): Call<ArrayList<Comment>>

    @POST("comments")
    fun addComment(
        @Query("author_name") author_name:String,
        @Query("author_email") author_email:String,
        @Query("content") content:String,
        @Query("post") post:Int
    ): Call<Comment>

}