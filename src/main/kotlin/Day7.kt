fun main() {
    println(Day7().part1("day7_data.txt".readFileDirectlyAsText()))
    println(Day7().part2("day7_data.txt".readFileDirectlyAsText()))
}

class Day7 {
    fun part1(text: String): Int {
        val root = parse(text)
        return (root as Directory).getSizesFilteredOnPart1().sum()
    }

    fun part2(text: String): Int {
        val root = parse(text)
        val spaceInUse = (root as Directory).size()
        val needed = FREE_SPACE_NEEDED - (TOTAL_SPACE_AVAILABLE - spaceInUse)
        return root.getSizesFilteredForPart2()
            .filter { it >= needed }
            .minOf { it }
    }

    fun parse(text: String): FileSystem {
        val commands = text.lines().drop(1)
        val root = Directory("/", parent = null)
        commands.fold(root) { acc, next -> acc.applyCommand(next) }
        return root
    }

}

const val FREE_SPACE_NEEDED = 30000000
const val TOTAL_SPACE_AVAILABLE = 70000000

sealed interface FileSystem
data class Directory(val name: String, val list: MutableList<FileSystem> = mutableListOf(), val parent: Directory?) : FileSystem {
    fun add(line: String): Directory {
        val (first, second) = Regex("(.+)\\s(.+)").matchEntire(line)!!.destructured
        val fs = when {
            first.startsWith("dir") -> Directory(second, parent=this)
            else -> File(second, first.toInt())
        }
        this.add(fs)

        return this
    }

    private fun add(fs: FileSystem): FileSystem {
        this.list.add(fs)
        return this
    }

    fun size(): Int =
        if (list.isEmpty()) {
            0
        } else {
            list.filterIsInstance<File>()
                .sumOf { it.size } +
                    list.filterIsInstance<Directory>().sumOf { it.size() }
        }

    fun applyCommand(line: String): Directory {
        val splits = line.split(" ")
        return if (splits[0] == "$") {
            if (splits[1] == "ls") {
                this
            } else {
                if ((splits[2] == "..")) {
                    this.parent!!
                } else {
                    this.list.filterIsInstance<Directory>().find { it.name == splits[2] }!!
                }
            }
        } else {
            this.add(line)
        }
    }

    fun getSizesFilteredOnPart1(): List<Int> {
        return if (list.isEmpty()) {
            emptyList()
        } else {
            val sizes = list.filterIsInstance<Directory>().map { it.size() }.filter { it < 100000 }
            return sizes + list.filterIsInstance<Directory>().flatMap { it.getSizesFilteredOnPart1() }
        }
    }
    fun getSizesFilteredForPart2(): List<Int> {
        return if (list.isEmpty()) {
            emptyList()
        } else {
            val sizes = list.filterIsInstance<Directory>().map { it.size() }
            return sizes + list.filterIsInstance<Directory>().flatMap { it.getSizesFilteredForPart2() }
        }
    }
}

data class File(val name: String, val size: Int) : FileSystem {
    fun size(): Int = size
}
