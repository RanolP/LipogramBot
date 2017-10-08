package io.github.ranolp.lipogrambot.core.filters.normalizers

import io.github.ranolp.lipogrambot.core.filters.SimpleWordFilter

object EnglishNormalizer : SimpleWordFilter.WordNormalizer{
    override fun normalize(word: Char) = word.toLowerCase().toString()

    override fun filter(word: Char) = word in 'a'..'z' || word in 'A'..'Z'
}