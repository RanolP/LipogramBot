package io.github.ranolp.lipogrambot.core.result

data class FilteredResult(val filteredWords: List<FilteredWord>) {
    val count: Int
        get() = filteredWords.size

    operator fun plus(filteredResult: FilteredResult) = FilteredResult(filteredWords + filteredResult.filteredWords)
}