package io.github.ranolp.lipogrambot.core.result

data class FilteredResult(val filteredWords: LinkedHashSet<FilteredWord>) {
    val count: Int
        get() = filteredWords.size

    operator fun plus(filteredResult: FilteredResult): FilteredResult {
        val tmp = LinkedHashSet<FilteredWord>()
        tmp += filteredWords
        tmp += filteredResult.filteredWords
        return FilteredResult(tmp)
    }
}