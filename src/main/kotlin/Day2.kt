import FileReader.readFileDirectlyAsText

fun main() {
//    val text = readFileDirectlyAsText("day2_test.txt")
    val text = readFileDirectlyAsText("day2_part1_data.txt")
    println(Day2().playPart2(text))
}

class Day2 {

    fun play(text: String) =
        processLines(text)
            .map { Game(it[0], it[1]) }
            .map { game -> game.resultPoints() }
            .sum()

    fun playPart2(text: String) =
        processLines(text)
            .map { it[0] to GamePart2(it[0], it[1]).pickMove() }
            .map { Game(it.first, it.second) }
            .map { game -> game.resultPoints() }
            .sum()

    private fun processLines(text: String) = text.lines()
        .map { line ->
            line.split(" ")
        }
}

data class Game(
    val opponent: String, // A=Rock, B=Paper, C=Scissors
    val me: String        // X=Rock, Y=Paper, Z=Scissors
) {


    fun resultPoints() =
        when (opponent) {
            "A" -> if (me == "X") 3 else if (me == "Y") 6 else 0
            "B" -> if (me == "Y") 3 else if (me == "Z") 6 else 0
            else -> if (me == "Z") 3 else if (me == "X") 6 else 0
        }.plus(
            when (me) {
                "X" -> 1
                "Y" -> 2
                else -> 3
            }
        )
}

data class GamePart2(
    val opponent: String, // A=Rock, B=Paper, C=Scissors
    val outcome: String   // X=Lose, Y=Draw, Z=Win
) {
    fun pickMove(): String =
        when (opponent) {
            "A" -> if (outcome == "X") "Z" else if (outcome == "Y") "X" else "Y"
            "B" -> if (outcome == "X") "X" else if (outcome == "Y") "Y" else "Z"
            else -> if (outcome == "X") "Y" else if (outcome == "Y") "Z" else "X"
        }

}