package roqay.task.newkhrogaty.view.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import roqay.task.khrogaty.models.comment.Comment
import roqay.task.newkhrogaty.api.details.DetailsFactory
import roqay.task.newkhrogaty.base.helpers.Resource
import roqay.task.newkhrogaty.view.features.details.fragments.Details

object DetailsRepository {

    fun getAllComments(post_id: Int): LiveData<Resource<String>> {
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        DetailsFactory.getAllComments(post_id)
            .enqueue(object : Callback<ArrayList<Comment>>{
                override fun onFailure(call: Call<ArrayList<Comment>>, t: Throwable) {
                    data.postValue(Resource.error(t.message))
                }

                override fun onResponse(
                    call: Call<ArrayList<Comment>>,
                    response: Response<ArrayList<Comment>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()?.size!! > 0){
                            Details.commentsList = response.body()!!
                            data.postValue(Resource.success(response.message()))
                        }else{
                            data.postValue(Resource.empty("empty",response.message()))
                        }
                    }
                }

            })

        return data
    }

    fun addComment(
        author_name:String,
        author_email:String,
        content:String,
        post:Int
    ): LiveData<Resource<String>> {
        val data = MutableLiveData<Resource<String>>()
        data.value = Resource.loading()

        DetailsFactory.addComment(
            author_name,
            author_email,
            content,
            post
        ).enqueue(object : Callback<Comment>{
            override fun onFailure(call: Call<Comment>, t: Throwable) {
                data.postValue(Resource.error(t.message))
            }

            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                if (response.isSuccessful){
                    Details.commentsList.add(response.body()!!)
                    data.postValue(Resource.success(response.message()))
                }else{
                    data.postValue(Resource.error(response.message()))
                }
            }

        })

        return data
    }
}