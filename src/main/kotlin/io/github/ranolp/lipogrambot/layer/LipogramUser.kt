package io.github.ranolp.lipogrambot.layer

import io.github.ranolp.lipogrambot.core.Lipogram
import io.github.ranolp.lipogrambot.core.layer.Platform

abstract class LipogramUser {
    // TODO: Save and load from database
    var lipogram: Lipogram = Lipogram()
    // TODO: Save and load from database
    var useLipogram: Boolean = false

    abstract val id: Long
    abstract val name: String
    abstract val platform: Platform
}