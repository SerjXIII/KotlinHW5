import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addTest() {
        val post = Post(
                copyHistory = emptyArray(),
                copyright = Post.Copyright(),
                text = "", donut = Post.Donut(),
                geo = Post.Geo(),
                attachments = emptyArray())
        WallService.add(post)

        assertEquals(1, post.id)
    }

    @Test
    fun addTest2() {
        val post = Post(
                copyHistory = emptyArray(),
                copyright = Post.Copyright(),
                text = "", donut = Post.Donut(),
                geo = Post.Geo(),
                attachments = emptyArray())
        WallService.add(post)
        val post2 = Post(
                copyHistory = emptyArray(),
                copyright = Post.Copyright(),
                text = "", donut = Post.Donut(),
                geo = Post.Geo(),
                attachments = emptyArray())
        WallService.add(post2)

        assertEquals(2, post2.id)
    }

    @Test
    fun updateTestTrue() {
        val post = Post(
                copyHistory = emptyArray(),
                copyright = Post.Copyright(),
                text = "", donut = Post.Donut(),
                geo = Post.Geo(),
                attachments = emptyArray())
        WallService.add(post)
        val newPost = Post(
                id = 1,
                copyHistory = emptyArray(),
                copyright = Post.Copyright(),
                text = "", donut = Post.Donut(),
                geo = Post.Geo(),
                attachments = emptyArray())

        assertTrue(WallService.update(newPost))
    }

    @Test
    fun updateTestFalse() {
        val post = Post(
                copyHistory = emptyArray(),
                copyright = Post.Copyright(),
                text = "", donut = Post.Donut(),
                geo = Post.Geo(),
                attachments = emptyArray())
        WallService.add(post)
        val newPost = Post(
                id = 2,
                copyHistory = emptyArray(),
                copyright = Post.Copyright(),
                text = "", donut = Post.Donut(),
                geo = Post.Geo(),
                attachments = emptyArray())

        assertFalse(WallService.update(newPost))
    }

    @Test
    fun createComment(){
        val post = Post(
            id = 1,
            copyHistory = emptyArray(),
            copyright = Post.Copyright(),
            text = "", donut = Post.Donut(),
            geo = Post.Geo(),
            attachments = emptyArray())

        WallService.add(post)

        WallService.createComment(1,Comment(1,1,))

        val id = WallService.getCommentId(0)

        assertEquals(1, id)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentException(){
        WallService.createComment(1,Comment(1,1,))
    }

}