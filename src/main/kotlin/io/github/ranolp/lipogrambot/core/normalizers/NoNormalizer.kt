package io.github.ranolp.lipogrambot.core.normalizers

import io.github.ranolp.lipogrambot.core.WordNormalizer

object NoNormalizer : WordNormalizer {
    override fun normalize(word: Char) = word.toString()
    override fun filter(word: Char) = true
}