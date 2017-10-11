package io.github.ranolp.lipogrambot.core.normalizers

import io.github.ranolp.lipogrambot.core.WordNormalizer

object EnglishNormalizer : WordNormalizer {
    override fun normalize(word: Char) = word.toLowerCase().toString()

    override fun filter(word: Char) = word in 'a'..'z' || word in 'A'..'Z'
}