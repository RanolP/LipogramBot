package io.github.ranolp.lipogrambot.core

interface WordNormalizer {
    fun normalize(word: Char): String

    fun filter(word: Char): Boolean
}