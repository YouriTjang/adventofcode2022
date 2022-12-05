import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun `deconstructOperationsTest`() {
        assertThat(Day5().deconstructInstruction("move 1 from 2 to 1")).isEqualTo(Instruction(1, 2, 1))
    }

    @Test
    fun parseTextFirstHalfTest() {
        val input =
            """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 """
        val expected = mapOf(
            1 to listOf('Z', 'N'),
            2 to listOf('M', 'C', 'D'),
            3 to listOf('P')
        )
        val map = Day5().parseTextFirstHalf(input)

        assertThat(map).isEqualTo(expected)
    }

    @Test
    fun preprocessSplitHalvesTest() {
        val input =
            """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 1 from 2 to 1"""
        val expected = listOf(
            """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 """,
            """move 1 from 2 to 1
move 1 from 2 to 1"""
        )

        assertThat(Day5().preprocessSplitHalves(input)).isEqualTo(expected)
    }

    @Test
    fun applyInstructionTest1() {
        val inputState = mapOf(
            1 to ArrayDeque(listOf('Z', 'N')),
            2 to ArrayDeque(listOf('M', 'C', 'D')),
            3 to ArrayDeque(listOf('P'))
        )

        val inputInstruction = Instruction(1, 1, 2)

        val expectedState = mapOf(
            1 to ArrayDeque(listOf('Z')),
            2 to ArrayDeque(listOf('M', 'C', 'D', 'N')),
            3 to ArrayDeque(listOf('P'))
        )

        assertThat( Day5().applyInstruction(inputState, inputInstruction)).isEqualTo(expectedState)
    }
    @Test
    fun applyInstructionTest2() {
        val inputState = mapOf(
            1 to ArrayDeque(listOf('Z', 'N')),
            2 to ArrayDeque(listOf('M', 'C', 'D')),
            3 to ArrayDeque(listOf('P'))
        )

        val inputInstruction = Instruction(2, 1, 2)

        val expectedState = mapOf(
            1 to ArrayDeque(listOf()),
            2 to ArrayDeque(listOf('M', 'C', 'D', 'N', 'Z')),
            3 to ArrayDeque(listOf('P'))
        )

        assertThat( Day5().applyInstruction(inputState, inputInstruction)).isEqualTo(expectedState)
    }

    @Test
    fun applyInstructionPart2Test1() {
        val inputState = mapOf(
            1 to ArrayDeque(listOf('Z', 'N')),
            2 to ArrayDeque(listOf('M', 'C', 'D')),
            3 to ArrayDeque(listOf('P'))
        )

        val inputInstruction = Instruction(1, 1, 2)

        val expectedState = mapOf(
            1 to ArrayDeque(listOf('Z')),
            2 to ArrayDeque(listOf('M', 'C', 'D', 'N')),
            3 to ArrayDeque(listOf('P'))
        )

        assertThat( Day5().applyInstructionPart2(inputState, inputInstruction)).isEqualTo(expectedState)
    }

    @Test
    fun applyInstructionPart2Test2() {
        val inputState = mapOf(
            1 to ArrayDeque(listOf('Z', 'N')),
            2 to ArrayDeque(listOf('M', 'C', 'D')),
            3 to ArrayDeque(listOf('P'))
        )

        val inputInstruction = Instruction(2, 1, 2)

        val expectedState = mapOf(
            1 to ArrayDeque(listOf()),
            2 to ArrayDeque(listOf('M', 'C', 'D', 'Z', 'N')),
            3 to ArrayDeque(listOf('P'))
        )

        assertThat( Day5().applyInstructionPart2(inputState, inputInstruction)).isEqualTo(expectedState)
    }


    @Test
    fun readTopContainersTest() {
        val inputState = mapOf(
            1 to ArrayDeque(listOf('Z', 'N')),
            2 to ArrayDeque(listOf('M', 'C', 'D')),
            3 to ArrayDeque(listOf('P'))
        )
        val result = Day5().readTopContainers(inputState)

        assertThat(result).isEqualTo("NDP")
    }
}
