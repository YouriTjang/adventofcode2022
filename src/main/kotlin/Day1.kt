import FileReader.readFileDirectlyAsText

fun main() {
    val text = readFileDirectlyAsText("day1_part1.txt")
    println(Day1().getMaxElf(text))
    println(Day1().getMaxElfs(text, 3))
}

class Day1 {
    fun getMaxElf(text: String): Int =
        getMaxElfs(text, 1)

    fun getMaxElfs(text: String, n: Int): Int =
        text.trimIndent()
            .split("\n\n")
            .map { it.split("\n").map { line -> line.toInt() } }
            .map { elfEntry -> elfEntry.sum() }
            .sortedDescending()
            .take(n)
            .sum()
}

