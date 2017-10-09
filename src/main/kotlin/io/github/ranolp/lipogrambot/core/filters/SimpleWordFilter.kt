package io.github.ranolp.lipogrambot.core.filters

import io.github.ranolp.lipogrambot.core.LipogramFilter
import io.github.ranolp.lipogrambot.core.filters.normalizers.NoNormalizer
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
            val chars = mutableListOf<Char>()
            for (ch in word) {
                (normalizers.firstOrNull { it.filter(ch) } ?: NoNormalizer).let {
                    it.normalize(ch).filter { it in filterTarget }.forEach {
                        chars += ch
                    }
                }
            }
            if (chars.isNotEmpty()) {
                result += FilteredWord(word, chars)
            }
        }
        return FilteredResult(LinkedHashSet(result))
    }
}