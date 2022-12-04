import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Test {


    @Test
    fun getDuplicateItemTest(){
        assertThat(Day3().getDuplicateItem("vJrwpWtwJgWrhcsFMMfFFhFp")).isEqualTo('p')
        assertThat(Day3().getDuplicateItem("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")).isEqualTo('L')
        assertThat(Day3().getDuplicateItem("PmmdzqPrVvPwwTWBwg")).isEqualTo('P')
        assertThat(Day3().getDuplicateItem("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn")).isEqualTo('v')
        assertThat(Day3().getDuplicateItem("ttgJtRGJQctTZtZT")).isEqualTo('t')
        assertThat(Day3().getDuplicateItem("CrZsJsPPZsGzwwsLwLmpwMDw")).isEqualTo('s')
    }

    @Test
    fun getHalvesTest(){
        val input = "vJrwpWtwJgWrhcsFMMfFFhFp"
        assertThat(Day3().getHalves(input)).isEqualTo(
            "vJrwpWtwJgWr" to "hcsFMMfFFhFp"
        )
    }

    @Test
    fun part1Test(){
        assertThat(Day3().part1("day3_test.txt")).isEqualTo(157)
    }

    @Test
    fun getPriorityTest(){
        assertThat(Day3().getPriority('p')).isEqualTo(16)
        assertThat(Day3().getPriority('L')).isEqualTo(38)
        assertThat(Day3().getPriority('P')).isEqualTo(42)
        assertThat(Day3().getPriority('v')).isEqualTo(22)
        assertThat(Day3().getPriority('t')).isEqualTo(20)
        assertThat(Day3().getPriority('s')).isEqualTo(19)
    }

    @Test
    fun getBadgeTest(){
        assertThat(Day3().getBadge(setOf("ab", "ac", "ad"))).isEqualTo('a')
        assertThat(Day3().getBadge(setOf("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg"))).isEqualTo('r')
        assertThat(Day3().getBadge(setOf("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"))).isEqualTo('Z')
    }
}