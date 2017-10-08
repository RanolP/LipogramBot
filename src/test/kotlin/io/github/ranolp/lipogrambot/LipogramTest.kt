package io.github.ranolp.lipogrambot

import io.github.ranolp.lipogrambot.core.Lipogram
import io.github.ranolp.lipogrambot.core.filters.SimpleWordFilter
import io.github.ranolp.lipogrambot.core.filters.normalizers.EnglishNormalizer
import io.github.ranolp.lipogrambot.core.filters.normalizers.KoreanNormalizer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class LipogramTest {
    @Test
    fun `test simple word filter for korean`() {
        val core = Lipogram()
        val filter = SimpleWordFilter().also {
            it += KoreanNormalizer
            it += 'ㅏ'
        }
        core += filter

        assertEquals(core.test("이것이 리얼 리포그램 채팅").count, 0)
        assertNotEquals(core.test("이 문장엔 스파이가 숨어있다.").count, 0)
    }

    @Test
    fun `test simple word filter for english`() {
        val core = Lipogram()
        val filter = SimpleWordFilter().also {
            it += EnglishNormalizer
            it += 'a'
        }
        core += filter
        assertEquals(core.test("except english's first letter").count, 0)
        assertNotEquals(core.test("it have AAAAAAAAAA").count, 0)
    }
}