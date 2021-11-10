package themis

import themis.types.swagger.*
import java.util.*
import kotlin.reflect.KClass

val todos: List<Nothing> = listOf(
    TODO("define all remaining spec definitions"),
    TODO("define all remaining DSL functions")
)

/**
 * A container class for the list of specifications to be defined.
 */
class SpecList {
    companion object {
        /**
         * A list of OpenAPI specifications.
         */
        val specs = mutableMapOf<String, Openapi>()
    }
}

/**
 * Gives the name of each element in an enum class as a list.
 */
fun KClass<out Enum<*>>.enumConstantNames() =
        this.java.enumConstants.map(Enum<*>::name)

/**
 * Gives the name of each element in an enum class without the first character
 * as a list.
 */
fun <E: Enum<E>> _enum(enum: KClass<E>): MutableList<String> {
    return enum.enumConstantNames().map{v -> v.removeRange(0, 1)}.toMutableList()
}

/**
 * Returns the non null value between value it is called on or
 * the parameter it was provided. If both are non-null it returns
 * the parameter it was provided.
 *
 * **Runs in the context of a [T] object.**
 */
infix fun <T>T.or(entity: T?): T {
    return entity ?: this
}

/**
 * Loads an environment variable.
 *
 * **Runs in the context of a [T] object.**
 */
infix fun <T>T.env(key: String): String? {
    var returnable: String? = null

    try {
        returnable = System.getenv(key)
    } catch (e: Exception) {
        error(e)
    }

    return returnable
}

/**
 * Constructs a [Schema] with a *type* of [Object] and *properties*
 * mapped from the provided parameter list.
 */
fun Object(vararg properties: Pair<String, Schema>): Schema { SpecList.specs
    val s = Schema(type = Object)
    s.properties = map(*properties);
    s.type = Object

    return s;
}

/**
 * Constructs a [Schema] with a *type* of [Array] and with *items* of
 * the resolution of the <code>block</code> provided.
 *
 * @param [block] A functions that returns a [Schema] object.
 */
fun Array(block: () -> Schema): Schema {
    val a = Schema(type = Array)
    val returnable = block()
    a.items = returnable;
    a.type = Array

    return a;
}

/**
 * Constructs a [MutableMap] of the provided parameter list.
 */
fun map(vararg pairs: Pair<String, Schema>): MutableMap<String, Schema> {
    return pairs.associate { (key, value) ->
        key to value
    }.toMutableMap()
}

/**
 * Pairs a [String] instance with a [Schema] object.
 *
 * **Runs in the context of a [String] object.**
 */
operator fun String.minus(type: Schema): Pair<String, Schema> {
    return this to type
}

/**
 * Initializes an [Openapi] object and put it the *specs* definition of
 * [SpecList] after mapping it with the [String] instance it was called
 * on.
 *
 * The [String] instance this function is called on should be the same
 * as the API version string to be used in the url of any request to the
 * API.
 *
 * **Runs in the context of a [String] object.**
 */
infix fun String.api(block: Openapi.() -> Unit): Openapi {
    SpecList.specs[this] = Openapi(
        openapi = "3.0.0",
        info = Info(
            title = "API Spec",
            version = "v1",
        )
    )

    block(SpecList.specs[this] as Openapi)

    return SpecList.specs[this] as Openapi
}

/**
 * Initiates the the *tag* element of an [Openapi] object if it is **null**.
 */
fun Openapi.initiateTags () {
    if (this.tags === null)
        this.tags = mutableListOf()
}

/**
 * Initiates the the *components* element of an [Openapi] object if it is
 * **null**.
 */
fun Openapi.initiateComponents () {
    if (this.components === null)
        this.components = Components()
}

/**
 * Sets the the *openapi* field of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
infix fun Openapi.openapi (openapiVersion: String) {
    this.openapi = openapiVersion
}

/**
 * Sets the the *title* field of the [Info] object
 * on an [Openapi] instance.
 *
 * **Runs in the context of an [Openapi] object.**
 */
infix fun Openapi.title (title: String) {
    this.info.title = title
}

/**
 * Sets the the *description* field of the [Info] object
 * on an [Openapi] instance.
 *
 * **Runs in the context of an [Openapi] object.**
 */
infix fun Openapi.description (description: String) {
    this.info.description = description
}

/**
 * Sets the the *version* field of the [Info] object
 * on an [Openapi] instance.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [version] The version of the specification. This should
 * not be confused with the version string provided for the url
 * when initializing an [Openapi] object.
 */
infix fun Openapi.version (version: String) {
    this.info.version = version
}

/**
 * Adds a [Server] object to the *servers* [List] in an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [block] A function that constructs a [Server] object.
 */
infix fun Openapi.server (block: Server.() -> Unit) {
    val s = Server("")
    s.block()
    this.servers.add(s)
}

/**
 * Sets the the *url* field of a [Server] object.
 *
 * **Runs in the context of a [Server] object.**
 */
infix fun Server.url(url: String) {
    this.url = url
}

/**
 * Sets the the *description* field of a [Server] object.
 *
 * **Runs in the context of a [Server] object.**
 */
infix fun Server.description(description: String) {
    this.description = description
}

/**
 * Adds a [Pair] of [kotlin.String] and [ServerVariable] object to
 * the *variables* [Map] in a [Server] object.
 *
 * **Runs in the context of an [Server] object.**
 *
 * @param [variableName] The name of the server variable.
 * @param [block] A function that constructs a [ServerVariable] object.
 */
fun Server.variable(variableName: String, block: ServerVariable.() -> Unit) {
    if (this.variables === null){
        this.variables = mutableMapOf()
    }

    val sv = ServerVariable(default = "")
    block(sv)
    this.variables?.set(variableName, sv)
}

/**
 * Sets the the *enum* field of a [ServerVariable] object. It uses
 * [_enum] to convert the enums to a [List] of [String].
 *
 * **Runs in the context of a [ServerVariable] object.**
 */
infix fun <E: Enum<E>>ServerVariable.enum(enum: KClass<E>) {
    this.enum = _enum(enum)
}
/**
 * Sets the the *default* field of a [ServerVariable] object.
 *
 * **Runs in the context of a [ServerVariable] object.**
 */
infix fun ServerVariable.default(default: String) {
    this.default = default
}

/**
 * Sets the the *description* field of a [ServerVariable] object.
 *
 * **Runs in the context of a [ServerVariable] object.**
 */
infix fun ServerVariable.description(description: String) {
    this.description = description
}

/**
 * Adds a [Tag] object to the *tags* [List] in an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [block] A function that constructs a [Tag] object.
 */
infix fun Openapi.tag (block: Tag.() -> Unit) {
    initiateTags()

    val t = Tag("")
    t.block()
    this.tags?.add(t)
}

/**
 * Sets the the *name* field of a [Tag] object.
 *
 * **Runs in the context of a [Tag] object.**
 */
infix fun Tag.name(name: String){
    this.name = name
}

/**
 * Sets the the *description* field of a [Tag] object.
 *
 * **Runs in the context of a [Tag] object.**
 */
infix fun Tag.description(description: String){
    this.description = description
}

/**
 * Sets the the *externalDocs* field of a [Tag] object.
 *
 * **Runs in the context of a [Tag] object.**
 */
infix fun Tag.externalDocs (externalDocsBlock: ExternalDocumentation.() -> Unit) {
    val ed = ExternalDocumentation("", "")
    ed.externalDocsBlock()
    this.externalDocs = ed
}

/**
 * Sets the the *description* field of an [ExternalDocumentation] object.
 *
 * **Runs in the context of an [ExternalDocumentation] object.**
 */
infix fun ExternalDocumentation.description(description: String){
    this.description = description
}

/**
 * Sets the the *url* field of an [ExternalDocumentation] object.
 *
 * **Runs in the context of an [ExternalDocumentation] object.**
 */
infix fun ExternalDocumentation.url(url: String){
    this.url = url
}

/**
 * Adds a [Pair] of [String] and [Schema] object to the **schemas**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [Schema] object.
 */
fun Openapi.schema (key: String, block: Schema.() -> Schema) {
    initiateComponents()

    if(this.components?.schemas === null)
        this.components?.schemas = mutableMapOf()

    this.components?.schemas?.put(key, block(Schema(type = themis.types.swagger.String)))
}

/**
 * Defines the [Schema] object it was called on.
 *
 * Note: This functions is not necessary. It is made for fluency.
 *
 * **Runs in the context of a [Schema] object.**
 *
 * @param [block] Returns a constructed [Schema] object.
 */
fun Schema.of(block: (Schema) -> Schema): Schema {
    return block(this)
}

/**
 * Adds a [Pair] of [String] and [Example] object to the **examples**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs an [Example] object.
 */
fun Openapi.example (key: String, block: Example.() -> Unit) {
    initiateComponents()

    if(this.components?.examples === null)
        this.components?.examples = mutableMapOf()

    val e = Example()
    e.block()
    this.components?.examples?.put(key, e)
}

/**
 * Sets the the *summary* field of an [Example] object.
 *
 * **Runs in the context of an [Example] object.**
 */
infix fun Example.summary(summary: String) {
    this.summary = summary
}

/**
 * Sets the the *description* field of an [Example] object.
 *
 * **Runs in the context of an [Example] object.**
 */
infix fun Example.description(description: String) {
    this.description = description
}

/**
 * Sets the the *value* field of an [Example] object.
 *
 * **Runs in the context of an [Example] object.**
 */
infix fun Example.value(value: String) {
    this.value = value.trimIndent().trim()
}

/**
 * Sets the the *externalValue* field of an [Example] object.
 *
 * **Runs in the context of an [Example] object.**
 */
infix fun Example.externalValue(externalValue: String) {
    this.externalValue = externalValue
}
