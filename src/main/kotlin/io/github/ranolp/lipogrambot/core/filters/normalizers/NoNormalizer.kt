package io.github.ranolp.lipogrambot.core.filters.normalizers

import io.github.ranolp.lipogrambot.core.filters.SimpleWordFilter

object NoNormalizer : SimpleWordFilter.WordNormalizer {
    override fun normalize(word: Char) = word.toString()
    override fun filter(word: Char) = true
}