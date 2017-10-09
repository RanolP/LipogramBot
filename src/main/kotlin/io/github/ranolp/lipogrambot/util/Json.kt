package io.github.ranolp.waffle.util

import com.github.salomonbrys.kotson.*
import com.google.gson.*
import java.io.IOException
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

inline fun <reified T : Any> JsonElement.byClass(key: String? = null, crossinline init: (JsonObject) -> T,
        noinline default: (() -> T)? = { init(JsonObject()) }): JsonObjectDelegate<T> {
    return JsonObjectDelegate(this.obj, { init(it.obj) }, { it.toJson() }, key, default)
}

inline fun <reified T : Any> JsonElement.byNullableClass(key: String? = null, crossinline init: (JsonObject) -> T,
        noinline default: (() -> T)? = null): NullableJsonObjectDelegate<T?> {
    return NullableJsonObjectDelegate(this.obj, { init(it.obj) }, { it?.toJson() ?: jsonNull }, key, default)
}

inline fun <reified T : Any> T?.toJson(): JsonElement = if (this === null) jsonNull else Json.parse(Json.GSON.typedToJson(
        this))

object Json {
    val PARSER = JsonParser()
    val GSON = GsonBuilder().create()
    val PRETTY_GSON = GsonBuilder().setPrettyPrinting().create()

    fun parse(json: String): JsonElement {
        return PARSER.parse(json)
    }

    fun parseFile(path: String, vararg paths: String): JsonElement {
        return parse(Paths.get(path, *paths))
    }

    fun parse(path: Path): JsonElement {
        return try {
            PARSER.parse(Files.newBufferedReader(path))
        } catch (e: IOException) {
            e.printStackTrace()
            JsonNull.INSTANCE
        }
    }

    fun parse(reader: Reader): JsonElement {
        return PARSER.parse(reader)
    }

    inline fun <reified T> fromJson(jsonObject: JsonObject): T {
        return GSON.fromJson(jsonObject, T::class.java)
    }
}

inline fun JsonElement.prettyPrint() {
    prettyPrint(::println)
}

inline fun JsonElement.prettyPrint(crossinline print: (String) -> Unit) {
    print(Json.PRETTY_GSON.toJson(this))
}