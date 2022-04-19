import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import uniq.UniqStrings
import java.io.FileNotFoundException

class UniqTest {

    private val file1 = File("end.txt")

    private fun assertFileContent(testName: String) {
        val file = File(testName)
        val content = file.readLines().joinToString("\n")
        val expectedContent = file1.readLines().joinToString("\n")
        assertEquals(expectedContent, content, "files are different")
    }

    @Test
    fun testUtility() {

        UniqStrings(i = false, u = false, c = false, s = 0, input = "begin.txt", output = "end.txt").output()
        assertFileContent("test1.txt")

        UniqStrings(i = true, u = false, c = false, s = 0, input = "begin.txt", output = "end.txt").output()
        assertFileContent("test2.txt")

        UniqStrings(i = true, u = true, c = false, s = 0, input = "begin.txt", output = "end.txt").output()
        assertFileContent("test3.txt")

        UniqStrings(i = true, u = true, c = true, s = 0, input = "begin.txt", output = "end.txt").output()
        assertFileContent("test4.txt")

        UniqStrings(i = true, u = true, c = true, s = 4, input = "begin.txt", output = "end.txt").output()
        assertFileContent("test5.txt")

        try{
            UniqStrings(i = true, u = true, c = true, s = 4, input = "begin.txt", output = "someOtherName.txt").output()
            throw AssertionError("FileNotFoundException expected")
        } catch (_: FileNotFoundException){

        }
    }


}