import FileReader.readFileDirectlyAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun part1Test() {
        val actual = Day4().part1("6-6,4-6")

        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun part2Test() {
        val actual = Day4().part2(readFileDirectlyAsText("day4_test.txt"))

        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun isContainedInEachOtherTest() {
        val a = setOf(6)
        val b = setOf(4, 5, 6)
        assertThat(Day4().isContainedInEachOther(a,b)).isTrue
    }

    @Test
    fun parseLineTest(){
        val actual = Day4().parseLine("2-4,6-8")

        assertThat(actual).isEqualTo(listOf(setOf(2,3,4), setOf(6,7,8)))

    }
}