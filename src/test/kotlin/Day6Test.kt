import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun part1Test() {
        assertThat(Day6().part1("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(5)
        assertThat(Day6().part1("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(6)
        assertThat(Day6().part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).isEqualTo(10)
        assertThat(Day6().part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(11)
    }

    @Test
    fun part2Test() {
        assertThat(Day6().part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).isEqualTo(19)
        assertThat(Day6().part2("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(23)
        assertThat(Day6().part2("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(23)
        assertThat(Day6().part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).isEqualTo(29)
        assertThat(Day6().part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(26)
    }
}
