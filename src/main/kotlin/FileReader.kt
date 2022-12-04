object FileReader {
    fun readFileDirectlyAsText(fileName: String): String = this::class.java.getResource(fileName).readText(Charsets.UTF_8)
}

fun String.readFileDirectlyAsText(): String = FileReader::class.java.getResource(this).readText(Charsets.UTF_8)
