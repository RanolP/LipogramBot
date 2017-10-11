package io.github.ranolp.lipogrambot.core.storage

import io.github.ranolp.lipogrambot.layer.LipogramGroup

class GroupStorage<T : LipogramGroup> : Storage<T>({ old, new ->
    new.lipogram = old.lipogram
})