package io.github.ranolp.lipogrambot.core.normalizers

import io.github.ranolp.lipogrambot.core.WordNormalizer

object KoreanNormalizer : WordNormalizer {
    private val CHOSEONG = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ".toCharArray()
    private val JUNGSEONG = mapOf('ㅘ' to "ㅗㅏ",
            'ㅙ' to "ㅗㅐ",
            'ㅚ' to "ㅗㅣ",
            'ㅝ' to "ㅜㅓ",
            'ㅞ' to "ㅜㅔ",
            'ㅟ' to "ㅜㅣ",
            'ㅢ' to "ㅡㅣ")
    private val JONGSEONG = arrayOf("") + "ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ".map { it.toString() }
    private val CONSONANT = mapOf('ㄲ' to "ㄱㄱ",
            'ㄳ' to "ㄱㅅ",
            'ㄵ' to "ㄴㅈ",
            'ㄶ' to "ㄴㅎ",
            'ㄸ' to "ㄷㄷ",
            'ㄺ' to "ㄹㄱ",
            'ㄻ' to "ㄹㅁ",
            'ㄼ' to "ㄹㅂ",
            'ㄽ' to "ㄹㅅ",
            'ㄿ' to "ㄹㅍ",
            'ㅀ' to "ㄹㅎ",
            'ㅃ' to "ㅂㅂ",
            'ㅄ' to "ㅂㅅ",
            'ㅆ' to "ㅅㅅ")

    override fun normalize(word: Char): String {
        val tmp = word.toInt() - 0xAC00
        val cho = CHOSEONG[(tmp - tmp % 28) / 28 / 21]
        val jung = ((tmp - tmp % 28) / 28 % 21 + 0x314F).toChar().let { JUNGSEONG[it] ?: it.toString() }
        val jong = JONGSEONG[tmp % 28]
        return cho + jung + jong
    }


    override fun filter(word: Char) = word in '가'..'힣'
}