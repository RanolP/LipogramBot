package io.github.ranolp.lipogrambot.layer

data class Permission(val requireAdmin: Boolean, val name: String) {
    companion object {
        val ADMIN = Permission(true, "*")
    }
}