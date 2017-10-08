package io.github.ranolp.lipogrambot.core.filters.normalizers

import io.github.ranolp.lipogrambot.core.filters.SimpleWordFilter

object KoreanNormalizer : SimpleWordFilter.WordNormalizer {
    private val CHOSEONG = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ".toCharArray()
    private val JONGSEONG = arrayOf("") + "ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ".map { it.toString() }
    override fun normalize(word: Char): String {
        val tmp = word.toInt() - 0xAC00
        val cho = CHOSEONG[(tmp - tmp % 28) / 28 / 21]
        val jung = ((tmp - tmp % 28) / 28 % 21 + 0x314F).toChar().toString()
        val jong = JONGSEONG[tmp % 28]
        return cho + jung + jong
    }


    override fun filter(word: Char) = word in '가'..'힣'
}