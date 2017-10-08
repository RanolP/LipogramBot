package io.github.ranolp.lipogrambot.core

import io.github.ranolp.lipogrambot.core.result.FilteredResult

interface LipogramFilter {
    fun filter(sentence: String): FilteredResult
}