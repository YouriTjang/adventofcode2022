fun main() {
    println(Day3().part1("day3_data.txt"))
    println(Day3().part2("day3_data.txt"))
}

class Day3 {
    fun part1(file: String): Int {
        val text = FileReader.readFileDirectlyAsText(file)
        return text.lines()
            .map { getDuplicateItem(it) }
            .map { getPriority(it) }
            .sum()
    }

    fun part2(file: String): Int {
        val text = FileReader.readFileDirectlyAsText(file)
        return text.lines()
            .chunked(3)
            .map { getBadge(it.toSet()) }
            .sumOf { getPriority(it) }
    }

    fun getPriority(it: Char) = when {
        it.isLowerCase() -> it.code - SMALL_A_CHAR_OFFSET
        else -> it.code - BIG_A_CHAR_OFFSET
    }

    fun getDuplicateItem(input: String): Char {
        val (left, right) = getHalves(input)
        return left.toSet().intersect(right.toSet()).first()
    }

    fun getHalves(input: String): Pair<String, String> {
        val middle = input.length / 2
        val left = input.substring(0 until middle)
        val right = input.substring(middle until input.length)
        return Pair(left, right)
    }

    fun getBadge(group: Set<String>): Char {
        return group
            .map { it.toSet() }
            .reduce { acc, chars -> acc.intersect(chars) }
            .first()
    }

    companion object {
        const val SMALL_A_CHAR_OFFSET = 96
        const val BIG_A_CHAR_OFFSET = 38
    }
}