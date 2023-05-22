import java.text.SimpleDateFormat
import java.util.Date


fun main() {
}

object WallService {
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        if (posts.any { it == posts[0] }) {
            post.id = (posts.last()).id + 1
        } else post.id = 1
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, _) in posts.withIndex()) {
            if (post.id == posts[index].id) {
                posts[index] = post
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
    }
}

data class Post(
    var id: Long = 0,
    val date: String? = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()),
    var text: String = "empty post",
    val likes: Likes = Likes(),
    val canPin: Boolean = true,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val isFavorite: Boolean = false,
    val makedAsAds: Boolean = false
) {
    class Likes(
        var count: Int = 0,
        val userLikes: Boolean = false,
        val canLike: Boolean = true,
        val canPublish: Boolean = true
    )
}

