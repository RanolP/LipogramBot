package io.github.ranolp.lipogrambot.core

import io.github.ranolp.lipogrambot.core.result.FilteredResult

class Lipogram {
    private val filters = mutableListOf<LipogramFilter>()

    @JvmName("addFilter") operator fun plusAssign(filter: LipogramFilter) {
        filters += filter
    }

    @JvmName("removeFilter") operator fun minusAssign(filter: LipogramFilter) {
        filters -= filter
    }

    fun test(sentence: String): FilteredResult {
        return filters.map { it.filter(sentence) }.fold(FilteredResult(listOf())) { a, b -> a + b }
    }
}