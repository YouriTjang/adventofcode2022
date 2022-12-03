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
    val opponent: String,
    val me: String
) {
    companion object {
        const val OPPONENT_ROCK = "A"
        const val OPPONENT_PAPER = "B"
        const val ROCK = "X"
        const val PAPER = "Y"
        const val SCISSORS = "Z"
        private const val LOSE = 0
        private const val DRAW = 3
        private const val WIN = 6
    }

    fun resultPoints() =
        when (opponent) {
            OPPONENT_ROCK -> if (me == ROCK) DRAW else if (me == PAPER) WIN else LOSE
            OPPONENT_PAPER -> if (me == PAPER) DRAW else if (me == SCISSORS) WIN else LOSE
            else -> if (me == SCISSORS) DRAW else if (me == ROCK) WIN else LOSE
        }.plus(
            when (me) {
                ROCK -> 1
                PAPER -> 2
                else -> 3
            }
        )
}

data class GamePart2(
    val opponent: String,
    val outcome: String
) {
    companion object {
        private const val LOSE = "X"
        private const val DRAW = "Y"
        private const val OPPONENT_ROCK = "A"
        private const val OPPONENT_PAPER = "B"
        private const val ROCK = "X"
        private const val PAPER = "Y"
        private const val SCISSORS = "Z"
    }

    fun pickMove(): String =
        when (opponent) {
            OPPONENT_ROCK -> if (outcome == LOSE) PAPER else if (outcome == DRAW) ROCK else SCISSORS
            OPPONENT_PAPER -> if (outcome == LOSE) ROCK else if (outcome == DRAW) SCISSORS else PAPER
            else -> if (outcome == LOSE) SCISSORS else if (outcome == DRAW) PAPER else ROCK
        }

}