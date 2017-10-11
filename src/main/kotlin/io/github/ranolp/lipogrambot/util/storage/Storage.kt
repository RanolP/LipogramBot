package io.github.ranolp.lipogrambot.core.storage

open class Storage<T>(val update: (T, T) -> Unit) {
    private val map = mutableMapOf<Long, T>()
    operator fun get(id: Long): T? = map[id]
    operator fun set(id: Long, t: T): T {
        val get = map[id]
        if (get !== null) {
            update(get, t)
        }
        map[id] = t
        return t
    }
}