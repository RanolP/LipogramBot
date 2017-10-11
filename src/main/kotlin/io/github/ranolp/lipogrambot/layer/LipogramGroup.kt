package io.github.ranolp.lipogrambot.layer

import io.github.ranolp.lipogrambot.core.Lipogram
import io.github.ranolp.lipogrambot.core.layer.Platform
import io.github.ranolp.lipogrambot.core.normalizers.EnglishNormalizer
import io.github.ranolp.lipogrambot.core.normalizers.KoreanNormalizer

abstract class LipogramGroup {
    // TODO: Save and load from database
    var lipogram = Lipogram().also {
        it += KoreanNormalizer
        it += EnglishNormalizer
        it += 'ㅏ'
    }

    abstract val id: Long
    abstract val users: List<LipogramUser>
    abstract val name: String
    abstract val platform: Platform

    abstract fun sendMessage(message: String)
}