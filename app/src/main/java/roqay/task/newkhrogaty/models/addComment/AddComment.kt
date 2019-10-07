package roqay.task.newkhrogaty.models.addComment

import roqay.task.khrogaty.models.addComment.Content
import roqay.task.khrogaty.models.addComment.Links

data class AddComment(
    val _links: Links,
    val acf: List<Any>,
    val author: Int,
    val author_avatar_urls: AuthorAvatarUrls,
    val author_name: String,
    val author_url: String,
    val content: Content,
    val date: String,
    val date_gmt: String,
    val id: Int,
    val link: String,
    val meta: List<Any>,
    val parent: Int,
    val post: Int,
    val status: String,
    val type: String
)