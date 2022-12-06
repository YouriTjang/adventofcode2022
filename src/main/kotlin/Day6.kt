fun main() {
    println(Day6().part1("day6_data.txt".readFileDirectlyAsText()))
    println(Day6().part2("day6_data.txt".readFileDirectlyAsText()))
}

class Day6 {
    fun part1(text: String): Int =
        getEndIndex(text, 4) + 1

    fun part2(text: String): Int =
        getEndIndex(text, 14) + 1

    private fun getEndIndex(text: String, windowSize: Int) = text
        .mapIndexed { idx, value -> idx to value }
        .asSequence()
        .windowed(windowSize)
        .map { areAllUnique(it, windowSize) }
        .filter { it != -1 }
        .first()

    private fun areAllUnique(pairs: List<Pair<Int, Char>>, size: Int): Int =
        if ((pairs.map { it.second }.toSet().size == size)) {
            pairs.last().first
        } else {
            -1
        }
}