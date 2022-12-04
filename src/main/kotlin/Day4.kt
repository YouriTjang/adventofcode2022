fun main() {
    println(Day4().part1("day4_data.txt".readFileDirectlyAsText()))
    println(Day4().part2("day4_data.txt".readFileDirectlyAsText()))
}

class Day4 {
    fun part1(text: String): Int {
        return text.lines()
            .map { parseLine(it) }
            .count { isContainedInEachOther(it[0], it[1]) }
    }

    fun part2(text: String): Int {
        return text.lines()
            .map { parseLine(it) }
            .count { it[0].overLapsWith(it[1]) }
    }

    fun parseLine(line: String) =
        line.split(",")
            .map { it.split("-") }
            .map { toSets(it) }

    private fun toSets(splitted: List<String>): Set<Int> {
        val left = splitted[0].toInt()
        val right = splitted[1].toInt()

        return left.rangeTo(right).toSet()
    }

    fun isContainedInEachOther(a: Set<Int>, b: Set<Int>): Boolean =
        a.containsAll(b) || b.containsAll(a)

    private fun Set<Int>.overLapsWith(that: Set<Int>) =
        this.intersect(that).isNotEmpty()
}