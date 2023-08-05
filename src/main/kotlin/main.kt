import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.Date


fun main() {
}

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun getCommentId(index:Long): Long{
        return comments[index.toInt()].id
    }

    fun add(post: Post): Post {
        if (posts.any { it == posts[0] }) {
            post.id = (posts.last()).id + 1
        } else post.id = 1
        posts += post.copy(likes = post.likes.copy())
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, _) in posts.withIndex()) {
            if (post.id == posts[index].id) {
                posts[index] = post.copy(likes = post.likes.copy())
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
    }

    fun createComment(postID: Long, comment: Comment): Comment {
        if (findPostByID(postID)) {
            comments += comment.copy()
            return comment
        } else throw PostNotFoundException("Post with id = $postID is not found.")
    }

    private fun findPostByID(postID: Long): Boolean {
        for (post in posts){
            if (post.id == postID) return true
        }
        return false
    }
}

data class Post(
    var id: Long = 0,
    val ownerID: Long = 0,
    val fromID: Long = 0,
    val createdBy: Long = 0,
    val date: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()),
    var text: String?,
    val replyOwnerID: Long = 0,
    val replyPostID: Long = 0,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val copyright: Copyright?,
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: String = "",
    val geo: Geo?,
    val signerID: Int = 0,
    val copyHistory: Array<Post>?,
    val canPin: Boolean = true,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val donut: Donut?,
    val postponedID: Int = 0,
    var attachments: Array<Attachment>?
) {
    class Comments(
        var count: Int = 0,
        val canPost: Boolean = true,
        val groupsCanPost: Boolean = true,
        val canClose: Boolean = false,
        val canOpen: Boolean = true
    )

    class Copyright(
        val id: Long = 0,
        val link: String = "no link",
        val name: String = "no name",
        val type: String = "no type"
    )

    data class Likes(
        var count: Int = 0,
        val userLikes: Boolean = false,
        val canLike: Boolean = true,
        val canPublish: Boolean = true
    )

    class Reposts(
        var count: Long = 0,
        val userReposted: Boolean = false
    )

    class Views(
        var count: Int = 0
    )

    class Geo(
        val type: String = "",
        val coordinates: String = "",
        val place: Place = Place()
    )

    class Place(
        val id: Long = 0,
        val title: String = "",
        val latitude: Int = 0,
        val longitude: Int = 0,
        val created: Int = 0,
        val icon: String = "",
        val checkins: Int = 0,
        val update: Int = 0,
        val type: Int = 0,
        val country: Int = 0,
        val city: Int = 0,
        val address: String = ""
    )

    class Donut(
        val isDonut: Boolean = false,
        val paidDuration: Int = 0,
        val placeholder: String = "no donut",
        val canPublishFreeCopy: Boolean = false,
        val editMode: String = "all"
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

interface Attachment {
    val type: String
}

data class VideoAttachment(var video: Video) : Attachment {
    override val type: String
        get() = "video"
}

data class Video(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var date: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()),
    var duration: Int = 0
)

data class AudioAttachment(var audio: Audio) : Attachment {
    override val type: String
        get() = "audio"
}

data class Audio(
    var id: Int = 0,
    var artist: String = "artist",
    var title: String = "title",
    var duration: Int = 0,
    var url: String = "link",
)

data class DocAttachment(var doc: Doc) : Attachment {
    override val type: String
        get() = "doc"
}

data class Doc(
    var id: Int = 0,
    var title: String = "title",
    var size: Int = 0,
    var ext: String = "ext",
    var url: String = "url",
    var type: Int = 0,
)

data class PhotoAttachment(var photo: Photo) : Attachment {
    override val type: String
        get() = "photo"
}

data class Photo(
    var id: Int = 0,
    var albumID: Int = 0,
    var text: String = "text",
    var width: Int = 0,
    var height: Int = 0
)

data class LinkAttachment(var link: Link) : Attachment {
    override val type: String
        get() = "link"
}

data class Link(
    var url: String = "url",
    var title: String = "title",
    var description: String = "description",
)

data class Comment(
    var id: Long = 0,
    val fromID: Long = 0,
    val date: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()),
    val text: String = "some comment"
)

class PostNotFoundException(message: String) : RuntimeException(message)