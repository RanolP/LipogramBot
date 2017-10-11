package io.github.ranolp.lipogrambot.core

import io.github.ranolp.lipogrambot.core.normalizers.NoNormalizer
import io.github.ranolp.lipogrambot.core.result.FilteredResult
import io.github.ranolp.lipogrambot.core.result.FilteredWord

class Lipogram {
    private val normalizers = mutableListOf<WordNormalizer>()
    private val filterTarget = mutableListOf<Char>()

    val setting = mutableMapOf<String, Any>()

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

    fun test(sentence: String): FilteredResult {
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