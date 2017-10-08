package io.github.ranolp.lipogrambot.core.filters

import io.github.ranolp.lipogrambot.core.LipogramFilter
import io.github.ranolp.lipogrambot.core.result.FilteredResult
import io.github.ranolp.lipogrambot.core.result.FilteredWord

class SimpleWordFilter : LipogramFilter {
    interface WordNormalizer {
        fun normalize(word: Char): String

        fun filter(word: Char): Boolean
    }

    private val normalizers = mutableListOf<WordNormalizer>()
    private val filterTarget = mutableListOf<Char>()

    @JvmName("addFilterTarget") operator fun plusAssign(ch: Char) {
        filterTarget += ch
    }

    @JvmName("removeFilterTarget") operator fun minusAssign(ch: Char) {
        filterTarget -= ch
    }

    @JvmName("addNormalizer") operator fun plusAssign(normalizer: WordNormalizer) {
        normalizers += normalizer
    }

    @JvmName("removeNormalizer") operator fun minusAssign(normalizer: WordNormalizer) {
        normalizers -= normalizer
    }

    override fun filter(sentence: String): FilteredResult {
        val result = mutableListOf<FilteredWord>()
        sentence.split(' ').forEach { word ->
            for ((index, ch) in word.withIndex()) {
                normalizers.firstOrNull { it.filter(ch) }?.let {
                    it.normalize(ch).filter { it in filterTarget }.forEach {
                        result += FilteredWord(sentence, index, word, ch)
                    }
                }
            }
        }
        return FilteredResult(result.toList())
    }
}