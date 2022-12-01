object FileReader {
    fun readFileDirectlyAsText(fileName: String): String = this::class.java.getResource(fileName).readText(Charsets.UTF_8)
}