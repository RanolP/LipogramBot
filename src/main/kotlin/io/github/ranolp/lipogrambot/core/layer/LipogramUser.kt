package io.github.ranolp.lipogrambot.core.layer

import io.github.ranolp.lipogrambot.core.Lipogram

abstract class LipogramUser {
    // TODO: Save and load from database
    var lipogram: Lipogram = Lipogram()
    // TODO: Save and load from database
    var useLipogram: Boolean = false

    abstract val id: Long
    abstract val name: String
    abstract val platform: Platform
}