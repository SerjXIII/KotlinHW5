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
        val post = Post()
        WallService.add(post)

        assertEquals(0, post.id)
    }

    @Test
    fun updateTestTrue() {
        val post = Post()
        WallService.add(post)
        val newPost = Post(id = 1)

        assertTrue(WallService.update(newPost))
    }

    @Test
    fun updateTestFalse() {
        val post = Post()
        WallService.add(post)
        val newPost = Post(id = 2)

        assertFalse(WallService.update(newPost))
    }
}