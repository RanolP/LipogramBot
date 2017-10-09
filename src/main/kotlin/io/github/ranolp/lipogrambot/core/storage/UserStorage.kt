package io.github.ranolp.lipogrambot.core.storage

import io.github.ranolp.lipogrambot.core.layer.LipogramUser

class UserStorage<T : LipogramUser> : Storage<T>({ old, new ->
    new.lipogram = old.lipogram
    new.useLipogram = old.useLipogram
})