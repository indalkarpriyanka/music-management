package com.appsfactory.musicmgmt.data.remote.network

import com.squareup.moshi.*

import java.lang.reflect.Type
import java.util.*


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@JsonQualifier
annotation class SingleToArray
class SingleToArrayAdapter(
    private val delegateAdapter: JsonAdapter<List<Any>>,
    private val elementAdapter: JsonAdapter<Any>
) : JsonAdapter<Any>() {

    companion object {
        val INSTANCE = SingleToArrayAdapterFactory()
    }

    override fun fromJson(reader: JsonReader): Any? =
        if (reader.peek() != JsonReader.Token.BEGIN_ARRAY) {
            Collections.singletonList(elementAdapter.fromJson(reader))
        } else delegateAdapter.fromJson(reader)

    override fun toJson(writer: JsonWriter, value: Any?) =
        throw UnsupportedOperationException("SingleToArrayAdapter is only used to deserialize objects")

    class SingleToArrayAdapterFactory : Factory {

        override fun create(
            type: Type,
            annotations: Set<Annotation>,
            moshi: Moshi
        ): JsonAdapter<Any>? {
            val delegateAnnotations = Types.nextAnnotations(annotations, SingleToArray::class.java)
                ?: return null
            if (Types.getRawType(type) != List::class.java) throw IllegalArgumentException("Only lists may be annotated with @SingleToArray. Found: $type")
            val elementType = Types.collectionElementType(type, List::class.java)
            val delegateAdapter: JsonAdapter<List<Any>> = moshi.adapter(type, delegateAnnotations)
            val elementAdapter: JsonAdapter<Any> = moshi.adapter(elementType)

            return SingleToArrayAdapter(delegateAdapter, elementAdapter)
        }
    }
}