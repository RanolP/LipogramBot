package io.github.ranolp.lipogrambot.core.layer

import io.github.ranolp.lipogrambot.core.Lipogram
import io.github.ranolp.lipogrambot.core.filters.SimpleWordFilter
import io.github.ranolp.lipogrambot.core.filters.normalizers.EnglishNormalizer
import io.github.ranolp.lipogrambot.core.filters.normalizers.KoreanNormalizer

abstract class LipogramGroup() {
    // TODO: Save and load from database
    var lipogram = Lipogram().also {
        it += SimpleWordFilter().also {
            it += KoreanNormalizer
            it += EnglishNormalizer
            it += 'ㅏ'
        }
    }

    abstract val id: Long
    abstract val users: List<LipogramUser>
    abstract val name: String
    abstract val platform: Platform

    abstract fun sendMessage(message: String)
}