import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7Test {
    @Test
    fun `part1 test`() {
        val actual = Day7().parse("day7_test.txt".readFileDirectlyAsText()) as Directory
        val sizeBy = actual.getSizesFilteredOnPart1()
        assertThat(sizeBy.sum()).isEqualTo(95437)
    }
    @Test
    fun `part2 test`() {
        val actual = Day7().part2("day7_test.txt".readFileDirectlyAsText())
        assertThat(actual).isEqualTo(24933642)
    }

    @Test
    fun `part1 test read file`() {
        val root = Directory("/", mutableListOf(), null)
        val txt = "105956 cwdsppc.hnn"
        val updated = root.add(txt)
        val file = updated.list[0] as File
        assertThat(file.name).isEqualTo("cwdsppc.hnn")
        assertThat(file.size).isEqualTo(105956)
    }

    @Test
    fun `part1 test read directory`() {
        val root = Directory("/", mutableListOf(), null)
        val txt = "dir dir"
        val updated = root.add(txt)
        val directory = updated.list[0] as Directory
        assertThat(directory.name).isEqualTo("dir")
    }

    @Test
    fun `part1 test sum directory`() {
        val root = Directory("/", mutableListOf(Directory("a", parent = null)), null)
        assertThat(root.size()).isEqualTo(0)
    }

    @Test
    fun `part1 test sum files`() {
        val root = Directory(
            "/",
            mutableListOf(
                File("a", 123),
                File("a", 123)
            ), null
        )
        assertThat(root.size()).isEqualTo(246)
    }

    @Test
    fun `part1 test sum files system`() {
        val root = Directory(
            "/",
            mutableListOf(
                Directory(
                    "a",
                    mutableListOf(
                        File("a", 1),
                        File("b", 3)
                    ), null
                ),
                File("c", 1),
                File("d", 2)
            ), null
        )
        assertThat(root.size()).isEqualTo(7)
    }

    @Test
    fun `parse part1 to files system`() {
        val expected = Directory(
            "/",
            mutableListOf(
                Directory(
                    "a",
                    mutableListOf(
                        File("a", 1),
                        File("b", 3)
                    ), null
                ),
                File("c", 1),
                File("d", 2)
            ), null
        )

        val text = """
            ${'$'} cd /
            ${'$'} ls
            dir brhvclj
            dir clnvqg
            dir dtqtvvrn
            dir lcz
        """.trimIndent()
        assertThat(Day7().parse(text)).isEqualTo(expected)
    }


    @Test
    fun `apply command to directory`() {
        val input = Directory(
            "/",
            mutableListOf(
                Directory(
                    "a",
                    mutableListOf(
                        File("a", 1),
                        File("b", 3)
                    ), null
                ),
                File("c", 1),
                File("d", 2)
            ), null
        )

        val expected = Directory(
            "a",
            mutableListOf(
                File("a", 1),
                File("b", 3)
            ), null
        )

        val actual = input.applyCommand("\$ cd a")
        assertThat(actual).isEqualTo(expected)

        val actual2 = actual.applyCommand("123 abc")
        val expected2 = Directory(
            "a",
            mutableListOf(
                File("a", 1),
                File("b", 3),
                File("abc", 123)
            ), null
        )
        assertThat(actual2).isEqualTo(expected2)

        val actual3 = actual2.applyCommand("\$ cd ..")
        assertThat(actual3.name).isEqualTo("/")
    }

    @Test
    fun `create filesystem and apply commands`() {
        val root = Directory("/", parent = null)
        root.applyCommand("dir a")
        root.applyCommand("dir b")

        assertThat(root.list.filterIsInstance<Directory>().map { it.name })
            .containsExactly("a", "b")

        val a = root.applyCommand("\$ cd a")
        a.applyCommand("dir c")
        a.applyCommand("dir d")

        assertThat(a.list.filterIsInstance<Directory>().map { it.name })
            .containsExactly("c", "d")

        val root2 = a.applyCommand("\$ cd /")
        assertThat(root2.name).isEqualTo("/")
    }

    @Test
    fun part2Test() {
    }
}

