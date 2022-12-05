fun main() {
    println(Day5().part1("day5_data.txt".readFileDirectlyAsText()))
    println(Day5().part2("day5_data.txt".readFileDirectlyAsText()))
}

typealias State = Map<Int, ArrayDeque<Char>>

class Day5 {
    fun part1(text: String): String {
        val splittedText = preprocessSplitHalves(text)

        val startState = parseTextFirstHalf(splittedText[0])
        val instructions = deconstructInstructions(splittedText[1])

        val endState = instructions
            .fold(startState) { acc, next -> Day5().applyInstruction(acc, next) }

        return readTopContainers(endState)
    }

    fun preprocessSplitHalves(input: String): List<String> {
        return input.split("\n\n")
    }


    fun parseTextFirstHalf(text: String): State {
        val numberOfContainers = text.lines().last().trim().last().digitToInt()
        return readStacks(text, numberOfContainers)
    }

    fun readStacks(input: String, numberOfContainers: Int): State {
        val result = mutableMapOf<Int, ArrayDeque<Char>>()
        for (stack in 0 until numberOfContainers) {
            val readStack = readStack(input, stack, numberOfContainers)
            result[stack + 1] = readStack
        }
        return result
    }


    private fun readStack(input: String, stack: Int, numberOfContainers: Int): ArrayDeque<Char> {
        val indices = generateSequence(1) { num -> num + 4 }.take(numberOfContainers).toList()

        val result = mutableListOf<Char>()
        val lines = input.lines().reversed().drop(1)
        val x = indices[stack]
        for (y in 0 until input.lines().size - 1) {
            result.add(lines[y][x])
        }
        return ArrayDeque(result.filter { it.isLetter() })
    }

    fun deconstructInstructions(text: String): List<Instruction> =
        text.lines()
            .map { deconstructInstruction(it) }


    fun deconstructInstruction(opStr: String): Instruction {
        val regex = Regex("""move\s(\d*)\sfrom\s(\d*)\sto\s(\d*)""")
        val (count, source, target) = regex.find(opStr)!!.destructured
        return Instruction(count.toInt(), source.toInt(), target.toInt())
    }

    fun applyInstruction(state: State, instruction: Instruction): State {
        val sourceStack = state[instruction.source]!!
        val targetStack = state[instruction.target]!!

        for (n in 1..instruction.count) {
            val container = sourceStack.removeLast()
            targetStack.addLast(container)
        }
        return state
    }

    fun readTopContainers(state: State): String =
        state.map { it.value.last() }
            .joinToString(separator = "") { it.toString() }

    fun part2(text: String): Int {
        TODO()
    }

}

data class Instruction(
    val count: Int,
    val source: Int,
    val target: Int
)
