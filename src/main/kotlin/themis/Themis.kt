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
fun Object(vararg properties: Pair<String, Schema>): Schema {
    val s = Schema(type = Object)
    s.properties = mutableMapOf(*properties);
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
 * Pairs a [String] instance with a value of type [T].
 *
 * **Runs in the context of a [String] object.**
 */
operator fun <T>String.minus(type: T): Pair<String, T> {
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
infix fun String.api(block: Openapi.() -> Openapi): Openapi {
    SpecList.specs[this] = Openapi(
        openapi = "3.0.0",
        info = Info(
            title = "API Spec",
            version = "v1",
        ),
        paths = mutableMapOf()
    )

    return block(SpecList.specs[this] as Openapi)
}

/**
 * Initiates the *tag* element of an [Openapi] object if it is **null**.
 */
fun Openapi.initiateTags () {
    if (this.tags === null)
        this.tags = mutableListOf()
}

/**
 * Initiates the *externalDocs* element of an [Openapi] object if it is **null**.
 */
fun Openapi.initiateExternalDocs () {
    if (this.externalDocs === null)
        this.externalDocs = ExternalDocumentation(url = "")
}

/**
 * Initiates the *components* element of an [Openapi] object if it is
 * **null**.
 */
fun Openapi.initiateComponents () {
    if (this.components === null)
        this.components = Components()
}

/**
 * Initiates a [PathsItem] object in the *paths* element of an
 * [Openapi] object if it is **null**.
 */
fun Openapi.initiatePath (path: String) {
    if (this.paths[path] == null) {
        this.paths[path] = PathsItem()
    }
}

/**
 * Initiates the *parameters* element of a [PathsItem] object if it
 * is **null**.
 */
fun PathsItem.initiateParameters() {
    if (this.parameters == null) {
        this.parameters = mutableListOf()
    }
}

/**
 * Initiates the *parameters* element of a [Operation] object if it
 * is **null**.
 */
fun Operation.initiateParameters() {
    if (this.parameters == null) {
        this.parameters = mutableListOf()
    }
}

/**
 * Initiates the *content* element of a [Parameter] object if it
 * is **null**.
 */
fun Parameter.initiateContent() {
    if(this.content === null)
        this.content = mutableMapOf()
}

/**
 * Initiates the *content* element of a [Header] object if it
 * is **null**.
 */
fun Header.initiateContent() {
    if(this.content === null)
        this.content = mutableMapOf()
}

/**
 * Initiates the *content* element of a [Response] object if it
 * is **null**.
 */
fun Response.initiateContent() {
    if(this.content === null)
        this.content = mutableMapOf()
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** Openapi ********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *openapi* field of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
infix fun Openapi.openapi (openapiVersion: String): Openapi {
    this.openapi = openapiVersion
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/*************************************************************** Info **********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *title* field of the [Info] object
 * on an [Openapi] instance.
 *
 * **Runs in the context of an [Openapi] object.**
 */
infix fun Openapi.title (title: String): Openapi {
    this.info.title = title
    return this
}

/**
 * Sets the the *description* field of the [Info] object
 * on an [Openapi] instance.
 *
 * **Runs in the context of an [Openapi] object.**
 */
infix fun Openapi.description (description: String): Openapi {
    this.info.description = description
    return this
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
infix fun Openapi.version (version: String): Openapi {
    this.info.version = version
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************** ServerComponent ****************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Server] object to the *servers* [List] in an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [block] A function that constructs a [Server] object.
 */
fun Openapi.server (block: Server.() -> Server): Openapi {
    if (this.servers == null) {
        this.servers =  mutableListOf()
    }

    this.servers?.add(block(Server("")))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** Server *********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *url* field of a [Server] object.
 *
 * **Runs in the context of a [Server] object.**
 */
infix fun Server.url(url: String): Server {
    this.url = url
    return this
}

/**
 * Sets the the *description* field of a [Server] object.
 *
 * **Runs in the context of a [Server] object.**
 */
infix fun Server.description(description: String): Server {
    this.description = description
    return this
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
fun Server.variable(variableName: String, block: ServerVariable.() -> ServerVariable): Server {
    if (this.variables === null){
        this.variables = mutableMapOf()
    }

    this.variables?.set(variableName, block(ServerVariable(default = "")))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************** ServerVariable *****************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *enum* field of a [ServerVariable] object.
 *
 * **Runs in the context of a [ServerVariable] object.**
 */
infix fun <E: Enum<E>>ServerVariable.enum(enum: KClass<E>): ServerVariable {
    this.enum = enum.enumConstantNames().toMutableList()
    return this
}
/**
 * Sets the the *default* field of a [ServerVariable] object.
 *
 * **Runs in the context of a [ServerVariable] object.**
 */
infix fun ServerVariable.default(default: String): ServerVariable {
    this.default = default
    return this
}

/**
 * Sets the the *description* field of a [ServerVariable] object.
 *
 * **Runs in the context of a [ServerVariable] object.**
 */
infix fun ServerVariable.description(description: String): ServerVariable {
    this.description = description
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************ TagComponent *****************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Tag] object to the *tags* [List] in an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [block] A function that constructs a [Tag] object.
 */
fun Openapi.tag (block: Tag.() -> Tag): Openapi {
    initiateTags()
    this.tags?.add(block(Tag("")))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/**************************************************************** Tag **********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *name* field of a [Tag] object.
 *
 * **Runs in the context of a [Tag] object.**
 */
infix fun Tag.name(name: String): Tag {
    this.name = name
    return this
}

/**
 * Sets the the *description* field of a [Tag] object.
 *
 * **Runs in the context of a [Tag] object.**
 */
infix fun Tag.description(description: String): Tag{
    this.description = description
    return this
}

/**
 * Sets the the *externalDocs* field of a [Tag] object.
 *
 * **Runs in the context of a [Tag] object.**
 */
fun Tag.externalDocs (block: ExternalDocumentation.() -> ExternalDocumentation): Tag {
    this.externalDocs = block(ExternalDocumentation("", ""))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** Openapi ********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the *externalDocs* field of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [block] A function that constructs an [ExternalDocumentation] object.
 */
fun Openapi.externalDocs (block: ExternalDocumentation.() -> ExternalDocumentation): Openapi {
    initiateExternalDocs()
    this.externalDocs = block(ExternalDocumentation(url = ""))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/******************************************************* ExternalDocumentation *************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *description* field of an [ExternalDocumentation] object.
 *
 * **Runs in the context of an [ExternalDocumentation] object.**
 */
infix fun ExternalDocumentation.description(description: String): ExternalDocumentation {
    this.description = description
    return this
}

/**
 * Sets the the *url* field of an [ExternalDocumentation] object.
 *
 * **Runs in the context of an [ExternalDocumentation] object.**
 */
infix fun ExternalDocumentation.url(url: String): ExternalDocumentation{
    this.url = url
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************** SchemaComponent ****************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and [Schema] object to the **schemas**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [Schema] object.
 */
fun Openapi.schema (key: String, block: Schema.() -> Schema): Openapi {
    initiateComponents()

    if(this.components?.schemas === null)
        this.components?.schemas = mutableMapOf()

    this.components?.schemas?.put(key, block(Schema(type = themis.types.swagger.String)))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** Schema *********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

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

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************* ResponseComponent ***************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and [Response] object to the **parameters**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs an [Response] object.
 */
fun Openapi.response (key: String, block: Response.() -> Response): Openapi {
    initiateComponents()

    if(this.components?.responses === null)
        this.components?.responses = mutableMapOf()

    this.components?.responses?.put(key, block(Response(description = "")))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************* Response ********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *description* field of a [Response] object.
 *
 * **Runs in the context of a [Response] object.**
 */
infix fun Response.description(description: String): Response {
    this.description = description
    return this
}

/**
 * Adds a [Pair] of [String] and [MediaType] object to the **content**
 * [Map] of a [Response] object.
 *
 * **Runs in the context of an [Response] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [MediaType] object.
 */
fun Response.header (key: String, block: Header.() -> Header): Response {
    if (this.headers == null)
        this.headers = mutableMapOf()

    this.headers?.put(key, block(Header()))
    return this
}

/**
 * Adds a [Pair] of [String] and [MediaType] object to the **content**
 * [Map] of a [Response] object.
 *
 * **Runs in the context of an [Response] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [MediaType] object.
 */
fun Response.contentOf(key: String, block: MediaType.() -> MediaType): Response {
    initiateContent()
    this.content?.put(key, block(MediaType()))
    return this
}

/**
 * Adds a [Pair] of string value **application/json** and a [MediaType]
 * object to the **content** [Map] of a [Response] object.
 *
 * **Runs in the context of an [Response] object.**
 *
 * @param [block] A function that constructs a [MediaType] object.
 */
fun Response.jsonContent(block: MediaType.() -> MediaType): Response {
    return this.contentOf("application/json", block)
}

/**
 * Adds a [Pair] of [String] and [Link] object to the **links**
 * [Map] of a [Response] object.
 *
 * **Runs in the context of an [Response] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [Link] object.
 */
fun Response.link(key: String, block: Link.() -> Link): Response {
    if(this.links == null)
        this.links = mutableMapOf()

    this.links?.put(key, block(Link()))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************* ParameterComponent **************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and [Parameter] object to the **parameters**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs an [Parameter] object.
 */
fun Openapi.parameter (key: String, block: Parameter.() -> Parameter): Openapi {
    initiateComponents()

    if(this.components?.parameters === null)
        this.components?.parameters = mutableMapOf()

    this.components?.parameters?.put(key, block(Parameter(name = "id", `in` = PATH)))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************* Parameter *******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *description* field of a [Parameter] object.
 *
 * **Runs in the context of a [Parameter] object.**
 */
infix fun Parameter.description(description: String): Parameter {
    this.description = description
    return this
}

/**
 * Sets the the *required* field of a [Parameter] object.
 *
 * **Runs in the context of a [Parameter] object.**
 */
infix fun Parameter.required(required: Boolean): Parameter {
    this.required = required
    return this
}

/**
 * Sets the the *deprecated* field of a [Parameter] object.
 *
 * **Runs in the context of a [Parameter] object.**
 */
infix fun Parameter.deprecated(deprecated: Boolean): Parameter {
    this.deprecated = deprecated
    return this
}

/**
 * Sets the the *allowEmptyValue* field of a [Parameter] object.
 *
 * **Runs in the context of a [Parameter] object.**
 */
infix fun Parameter.allowEmptyValue(allowEmptyValue: Boolean): Parameter {
    this.allowEmptyValue = allowEmptyValue
    return this
}

/**
 * Sets the the *style* field of a [Parameter] object.
 *
 * **Runs in the context of a [Parameter] object.**
 */
infix fun Parameter.style(style: String): Parameter {
    this.style = style
    return this
}

/**
 * Sets the the *explode* field of a [Parameter] object.
 *
 * **Runs in the context of a [Parameter] object.**
 */
infix fun Parameter.explode(explode: Boolean): Parameter {
    this.explode = explode
    return this
}

/**
 * Sets the the *allowReserved* field of a [Parameter] object.
 *
 * **Runs in the context of a [Parameter] object.**
 */
infix fun Parameter.allowReserved(allowReserved: Boolean): Parameter {
    this.allowReserved = allowReserved
    return this
}

/**
 * Sets the the *example* field of a [Parameter] object.
 *
 * **Runs in the context of a [Parameter] object.**
 */
infix fun Parameter.example(example: Any): Parameter {
    this.example = example
    return this
}

/**
 * Adds a [Pair] of [String] and [Example] object to the **examples**
 * [Map] of a [Response] object.
 *
 * **Runs in the context of an [Response] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [Example] object.
 */
fun Parameter.example(key: String, block: Example.() -> Example): Parameter {
    if(this.examples === null)
        this.examples = mutableMapOf()

    this.examples?.put(key, block(Example()))
    return this
}

/**
 * Adds a [Pair] of [String] and [MediaType] object to the **content**
 * [Map] of a [Parameter] object.
 *
 * **Runs in the context of an [Parameter] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [MediaType] object.
 */
fun Parameter.contentOf(key: String, block: MediaType.() -> MediaType): Parameter {
    initiateContent()
    this.content?.put(key, block(MediaType()))
    return this
}

/**
 * Adds a [Pair] of string value **application/json** and a [MediaType]
 * object to the **content** [Map] of a [Parameter] object.
 *
 * **Runs in the context of an [Parameter] object.**
 *
 * @param [block] A function that constructs a [MediaType] object.
 */
fun Parameter.jsonContent(block: MediaType.() -> MediaType): Parameter {
    return this.contentOf("application/json", block)
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************** ExampleComponent ***************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and [Example] object to the **examples**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs an [Example] object.
 */
fun Openapi.example (key: String, block: Example.() -> Example): Openapi {
    initiateComponents()

    if(this.components?.examples === null)
        this.components?.examples = mutableMapOf()

    this.components?.examples?.put(key, block(Example()))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** Example ********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *summary* field of an [Example] object.
 *
 * **Runs in the context of an [Example] object.**
 */
infix fun Example.summary(summary: String): Example {
    this.summary = summary
    return this
}

/**
 * Sets the the *description* field of an [Example] object.
 *
 * **Runs in the context of an [Example] object.**
 */
infix fun Example.description(description: String): Example {
    this.description = description
    return this
}

/**
 * Sets the the *value* field of an [Example] object.
 *
 * **Runs in the context of an [Example] object.**
 */
infix fun Example.value(value: String): Example {
    this.value = value.trimIndent().trim()
    return this
}

/**
 * Sets the the *externalValue* field of an [Example] object.
 *
 * **Runs in the context of an [Example] object.**
 */
infix fun Example.externalValue(externalValue: String): Example {
    this.externalValue = externalValue
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/******************************************************** RequestBodyComponent *************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and [RequestBody] object to the **headers**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs an [RequestBody] object.
 */
fun Openapi.requestBody (key: String, block: RequestBody.() -> RequestBody): Openapi {
    initiateComponents()

    if(this.components?.requestBodies === null)
        this.components?.requestBodies = mutableMapOf()

    this.components?.requestBodies?.put(key, block(RequestBody(content = mutableMapOf())))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************ RequestBody ******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *description* field of a [RequestBody] object.
 *
 * **Runs in the context of a [RequestBody] object.**
 */
infix fun RequestBody.description(description: String): RequestBody {
    this.description = description
    return this
}

/**
 * Adds a [Pair] of [String] and [MediaType] object to the **content**
 * [Map] of a [RequestBody] object.
 *
 * **Runs in the context of an [RequestBody] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [MediaType] object.
 */
fun RequestBody.contentOf(key: String, block: MediaType.() -> MediaType): RequestBody {
    this.content[key] = block(MediaType())
    return this
}

/**
 * Adds a [Pair] of string value **application/json** and a [MediaType]
 * object to the **content** [Map] of a [RequestBody] object.
 *
 * **Runs in the context of an [RequestBody] object.**
 *
 * @param [block] A function that constructs a [MediaType] object.
 */
fun RequestBody.jsonContent(block: MediaType.() -> MediaType): RequestBody {
    return this.contentOf("application/json", block)
}

/**
 * Sets the the *required* field of a [RequestBody] object.
 *
 * **Runs in the context of a [RequestBody] object.**
 */
infix fun RequestBody.required(required: Boolean): RequestBody {
    this.required = required
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************** HeaderComponent ****************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and [Header] object to the **headers**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs an [Header] object.
 */
fun Openapi.header (key: String, block: Header.() -> Header): Openapi {
    initiateComponents()

    if(this.components?.headers === null)
        this.components?.headers = mutableMapOf()

    this.components?.headers?.put(key, block(Header()))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/*************************************************************** Header ********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *description* field of a [Header] object.
 *
 * **Runs in the context of a [Header] object.**
 */
infix fun Header.description(description: String): Header {
    this.description = description
    return this
}

/**
 * Sets the the *required* field of a [Header] object.
 *
 * **Runs in the context of a [Header] object.**
 */
infix fun Header.required(required: Boolean): Header {
    this.required = required
    return this
}

/**
 * Sets the the *deprecated* field of a [Header] object.
 *
 * **Runs in the context of a [Header] object.**
 */
infix fun Header.deprecated(deprecated: Boolean): Header {
    this.deprecated = deprecated
    return this
}

/**
 * Sets the the *allowEmptyValue* field of a [Header] object.
 *
 * **Runs in the context of a [Header] object.**
 */
infix fun Header.allowEmptyValue(allowEmptyValue: Boolean): Header {
    this.allowEmptyValue = allowEmptyValue
    return this
}

/**
 * Sets the the *style* field of a [Header] object.
 *
 * **Runs in the context of a [Header] object.**
 */
infix fun Header.style(style: String): Header {
    this.style = style
    return this
}

/**
 * Sets the the *explode* field of a [Header] object.
 *
 * **Runs in the context of a [Header] object.**
 */
infix fun Header.explode(explode: Boolean): Header {
    this.explode = explode
    return this
}

/**
 * Sets the the *allowReserved* field of a [Header] object.
 *
 * **Runs in the context of a [Header] object.**
 */
infix fun Header.allowReserved(allowReserved: Boolean): Header {
    this.allowReserved = allowReserved
    return this
}

/**
 * Sets the the *example* field of a [Header] object.
 *
 * **Runs in the context of a [Header] object.**
 */
infix fun Header.example(example: Any): Header {
    this.example = example
    return this
}

/**
 * Adds a [Pair] of [String] and [Example] object to the **examples**
 * [Map] of a [Header] object.
 *
 * **Runs in the context of an [Header] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [Example] object.
 */
fun Header.example(key: String, block: Example.() -> Example): Header {
    if(this.examples === null)
        this.examples = mutableMapOf()

    this.examples?.put(key, block(Example()))
    return this
}

/**
 * Adds a [Pair] of [String] and [MediaType] object to the **content**
 * [Map] of a [Header] object.
 *
 * **Runs in the context of an [Header] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [MediaType] object.
 */
fun Header.contentOf(key: String, block: MediaType.() -> MediaType): Header {
    initiateContent()
    this.content?.put(key, block(MediaType()))
    return this
}

/**
 * Adds a [Pair] of string value **application/json** and a [MediaType]
 * object to the **content** [Map] of a [Header] object.
 *
 * **Runs in the context of an [Header] object.**
 *
 * @param [block] A function that constructs a [MediaType] object.
 */
fun Header.jsonContent(block: MediaType.() -> MediaType): Header {
    return this.contentOf("application/json", block)
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/****************************************************** SecuritySchemeComponent ************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and [SecurityScheme] object of *apiKey*
 * to the **securitySchemes** [Map] with in the *components* element
 * of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [SecurityScheme] object
 * of an *apiKey* type.
 */
fun Openapi.apiKeySecurityScheme (key: String, block: SecurityScheme.() -> SecurityScheme): Openapi {
    initiateComponents()

    if(this.components?.securitySchemes === null)
        this.components?.securitySchemes = mutableMapOf()

    this.components?.securitySchemes?.put(key, block(SecurityScheme(type = API_KEY)))
    return this
}

/**
 * Adds a [Pair] of [String] and [SecurityScheme] object of *http*
 * to the **securitySchemes** [Map] with in the *components* element
 * of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [SecurityScheme] object
 * of an *http* type.
 */
fun Openapi.httpSecurityScheme (key: String, block: SecurityScheme.() -> SecurityScheme): Openapi {
    initiateComponents()

    if(this.components?.securitySchemes === null)
        this.components?.securitySchemes = mutableMapOf()

    this.components?.securitySchemes?.put(key, block(SecurityScheme(type = HTTP)))
    return this
}

/**
 * Adds a [Pair] of [String] and [SecurityScheme] object of *oauth2*
 * to the **securitySchemes** [Map] with in the *components* element
 * of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [SecurityScheme] object
 * of an *oauth2* type.
 */
fun Openapi.oauth2SecurityScheme (key: String, block: SecurityScheme.() -> SecurityScheme): Openapi {
    initiateComponents()

    if(this.components?.securitySchemes === null)
        this.components?.securitySchemes = mutableMapOf()

    this.components?.securitySchemes?.put(key, block(SecurityScheme(type = OAUTH2)))
    return this
}

/**
 * Adds a [Pair] of [String] and [SecurityScheme] object of *openIdConnect*
 * to the **securitySchemes** [Map] with in the *components* element
 * of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [SecurityScheme] object
 * of an *openIdConnect* type.
 */
fun Openapi.openIdConnectSecurityScheme (key: String, block: SecurityScheme.() -> SecurityScheme): Openapi {
    initiateComponents()

    if(this.components?.securitySchemes === null)
        this.components?.securitySchemes = mutableMapOf()

    this.components?.securitySchemes?.put(key, block(SecurityScheme(type = OPEN_ID_CONNECT)))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/*********************************************************** SecurityScheme ****************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *description* field of a [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
infix fun SecurityScheme.description(description: String): SecurityScheme {
    this.description = description
    return this
}

/**
 * Sets the the *name* field of a [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
infix fun SecurityScheme.name(name: String): SecurityScheme {
    this.name = name
    return this
}

/**
 * Sets the the *in* field of a [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
infix fun SecurityScheme.`in`(`in`: String): SecurityScheme {
    this.`in` = `in`
    return this
}

/**
 * Sets the the *scheme* field of a [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
infix fun SecurityScheme.scheme(scheme: String): SecurityScheme {
    this.scheme = scheme
    return this
}

/**
 * Sets the the *bearerFormat* field of a [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
infix fun SecurityScheme.bearerFormat(bearerFormat: String): SecurityScheme {
    this.bearerFormat = bearerFormat
    return this
}

/**
 * Sets the the *implicitFlow* field of the *flows* element in a
 * [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
fun SecurityScheme.implicitFlow(block: OAuthFlow.() -> OAuthFlow): SecurityScheme {
    if(this.flows == null)
        this.flows = OAuthFlows()

    this.flows?.implicit = block(OAuthFlow())
    return this
}

/**
 * Sets the the *passwordFlow* field of the *flows* element in a
 * [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
fun SecurityScheme.passwordFlow(block: OAuthFlow.() -> OAuthFlow): SecurityScheme {
    if(this.flows == null)
        this.flows = OAuthFlows()

    this.flows?.password = block(OAuthFlow())
    return this
}

/**
 * Sets the the *clientCredentialsFlow* field of the *flows* element in a
 * [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
fun SecurityScheme.clientCredentialsFlow(block: OAuthFlow.() -> OAuthFlow): SecurityScheme {
    if(this.flows == null)
        this.flows = OAuthFlows()

    this.flows?.clientCredentials = block(OAuthFlow())
    return this
}

/**
 * Sets the the *authorizationCodeFlow* field of the *flows* element in a
 * [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
fun SecurityScheme.authorizationCodeFlow(block: OAuthFlow.() -> OAuthFlow): SecurityScheme {
    if(this.flows == null)
        this.flows = OAuthFlows()

    this.flows?.authorizationCode = block(OAuthFlow())
    return this
}

/**
 * Sets the the *openIdConnectUrl* field of a [SecurityScheme] object.
 *
 * **Runs in the context of a [SecurityScheme] object.**
 */
infix fun SecurityScheme.openIdConnectUrl(openIdConnectUrl: String): SecurityScheme {
    this.openIdConnectUrl = openIdConnectUrl
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************* OAuthFlow *******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *authorizationUrl* field of a [OAuthFlow] object.
 *
 * **Runs in the context of a [OAuthFlow] object.**
 */
infix fun OAuthFlow.authorizationUrl(authorizationUrl: String): OAuthFlow {
    this.authorizationUrl = authorizationUrl
    return this
}

/**
 * Sets the the *tokenUrl* field of a [OAuthFlow] object.
 *
 * **Runs in the context of a [OAuthFlow] object.**
 */
infix fun OAuthFlow.tokenUrl(tokenUrl: String): OAuthFlow {
    this.tokenUrl = tokenUrl
    return this
}

/**
 * Sets the the *refreshUrl* field of a [OAuthFlow] object.
 *
 * **Runs in the context of a [OAuthFlow] object.**
 */
infix fun OAuthFlow.refreshUrl(refreshUrl: String): OAuthFlow {
    this.refreshUrl = refreshUrl
    return this
}

/**
 * Sets the the *scopes* field of a [OAuthFlow] object.
 *
 * **Runs in the context of a [OAuthFlow] object.**
 */
fun OAuthFlow.scopes(vararg scopes: Pair<String, String>): OAuthFlow {
    this.scopes = mutableMapOf(*scopes)
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************ LinkComponent ****************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and [Link] object to the **links**
 * [Map] with in the *components* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [Link] object.
 */
fun Openapi.link (key: String, block: Link.() -> Link): Openapi {
    initiateComponents()

    if(this.components?.links === null)
        this.components?.links = mutableMapOf()

    this.components?.links?.put(key, block(Link()))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/**************************************************************** Link *********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *description* field of a [Link] object.
 *
 * **Runs in the context of a [Link] object.**
 */
infix fun Link.description(description: String): Link {
    this.description = description
    return this
}

/**
 * Sets the the *operationRef* field of a [Link] object.
 *
 * **Runs in the context of a [Link] object.**
 */
infix fun Link.operationRef(operationRef: String): Link {
    this.operationRef = operationRef
    return this
}

/**
 * Sets the the *operationId* field of a [Link] object.
 *
 * **Runs in the context of a [Link] object.**
 */
infix fun Link.operationId(operationId: String): Link {
    this.operationId = operationId
    return this
}

/**
 * Sets the the *requestBody* field of a [Link] object.
 *
 * **Runs in the context of a [Link] object.**
 */
infix fun Link.requestBody(requestBody: String): Link {
    this.requestBody = requestBody
    return this
}

/**
 * Sets the the *parameters* field of a [Link] object with a map of
 * the arguments provided.
 *
 * **Runs in the context of a [Link] object.**
 */
fun Link.parameters(vararg parameters: Pair<String, String>): Link {
    this.parameters = mutableMapOf(*parameters)
    return this
}

/**
 * Sets the the *server* field of a [Link] object.
 *
 * **Runs in the context of a [Link] object.**
 */
fun Link.server (block: Server.() -> Server): Link {
    this.server = block(Server(""))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/********************************************************** CallbackComponent **************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Pair] of [String] and a <[String], [PathsItem]> pair to
 * the **callbacks** [Map] with in the *components* element of an
 * [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a <[String], [PathsItem]> pair.
 */
fun Openapi.callback (key: String, block: Callback.() -> Callback): Openapi {
    initiateComponents()

    if(this.components?.callbacks === null) {
        this.components?.callbacks = mutableMapOf(Pair(key, mutableMapOf()))
    }

    this.components?.callbacks?.put(key, this.components?.callbacks!![key]!!)
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** Callback *******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [Callback] . [Pair] to a [Callback] object.
 *
 * **Runs in the context of an [Callback] object.**
 */
fun Callback.on(key: String, block: PathsItem.() -> PathsItem): Callback {
    this[key] = block(PathsItem())
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** Openapi ********************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [SecurityRequirement] object to the *servers* [List] in
 * an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.securityRequirement(vararg securityRequirements: Pair<String, Array<String>>): Openapi {
    if(this.security == null)
        this.security = mutableListOf()

    this.security?.add(mutableMapOf(*securityRequirements))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/*************************************************************** Openapi *******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Adds a [PathsItem] object to the *paths* element in an [Openapi]
 * object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.describePath(path: String, block: PathsItem.() -> PathsItem): Openapi {
    if (this.paths[path] == null) this.paths[path] = block(PathsItem())
    else this.paths[path]?.block()
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************* PathsItem *******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *$ref* field of a [PathsItem] object.
 *
 * **Runs in the context of a [PathsItem] object.**
 */
infix fun PathsItem.`$ref`(`$ref`: String): PathsItem {
    this.`$ref` = `$ref`
    return this
}

/**
 * Sets the the *summary* field of a [PathsItem] object.
 *
 * **Runs in the context of a [PathsItem] object.**
 */
infix fun PathsItem.summary(summary: String): PathsItem {
    this.summary = summary
    return this
}

/**
 * Sets the the *description* field of a [PathsItem] object.
 *
 * **Runs in the context of a [PathsItem] object.**
 */
infix fun PathsItem.description(description: String): PathsItem {
    this.description = description
    return this
}

/**
 * Adds a [Server] object to the *servers* [List] in a [PathsItem] object.
 *
 * **Runs in the context of an [PathsItem] object.**
 *
 * @param [block] A function that constructs a [Server] object.
 */
fun PathsItem.server(block: Server.() -> Server): PathsItem {
    if (this.servers == null) {
        this.servers = mutableListOf()
    }

    this.servers?.add(block(Server("")))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Parameter] object of type **query**
 * to the **parameters** [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Parameter] object.
 */
fun PathsItem.query(name: String, block: Parameter.() -> Parameter): PathsItem {
    initiateParameters()
    this.parameters?.add(block(Parameter(`in` = QUERY, name = name)))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Parameter] object of type **header**
 * to the **parameters** [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Parameter] object.
 */
fun PathsItem.header(name: String, block: Parameter.() -> Parameter): PathsItem {
    initiateParameters()
    this.parameters?.add(block(Parameter(`in` = HEADER, name = name)))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Parameter] object of type **path**
 * to the **parameters** [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Parameter] object.
 */
fun PathsItem.path(name: String, block: Parameter.() -> Parameter): PathsItem {
    initiateParameters()
    this.parameters?.add(block(Parameter(`in` = PATH, name = name)))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Parameter] object of type **cookie**
 * to the **parameters** [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Parameter] object.
 */
fun PathsItem.cookie(name: String, block: Parameter.() -> Parameter): PathsItem {
    initiateParameters()
    this.parameters?.add(block(Parameter(`in` = COOKIE, name = name)))
    return this
}

/**
 * Adds a [Operation] object to the *get* element on the specified
 * *indexing path* in the *paths* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.get(path: String, block: Operation.() -> Operation): Openapi {
    initiatePath(path)
    this.paths[path]?.get = block(Operation())
    return this
}

/**
 * Adds a [Operation] object to the *put* element on the specified
 * *indexing path* in the *paths* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.put(path: String, block: Operation.() -> Operation): Openapi {
    initiatePath(path)
    this.paths[path]?.put = block(Operation())
    return this
}

/**
 * Adds a [Operation] object to the *post* element on the specified
 * *indexing path* in the *paths* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.post(path: String, block: Operation.() -> Operation): Openapi {
    initiatePath(path)
    this.paths[path]?.post = block(Operation())
    return this
}

/**
 * Adds a [Operation] object to the *delete* element on the specified
 * *indexing path* in the *paths* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.delete(path: String, block: Operation.() -> Operation): Openapi {
    initiatePath(path)
    this.paths[path]?.delete = block(Operation())
    return this
}

/**
 * Adds a [Operation] object to the *options* element on the specified
 * *indexing path* in the *paths* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.options(path: String, block: Operation.() -> Operation): Openapi {
    initiatePath(path)
    this.paths[path]?.options = block(Operation())
    return this
}

/**
 * Adds a [Operation] object to the *head* element on the specified
 * *indexing path* in the *paths* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.head(path: String, block: Operation.() -> Operation): Openapi {
    initiatePath(path)
    this.paths[path]?.head = block(Operation())
    return this
}

/**
 * Adds a [Operation] object to the *patch* element on the specified
 * *indexing path* in the *paths* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.patch(path: String, block: Operation.() -> Operation): Openapi {
    initiatePath(path)
    this.paths[path]?.patch = block(Operation())
    return this
}

/**
 * Adds a [Operation] object to the *trace* element on the specified
 * *indexing path* in the *paths* element of an [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 */
fun Openapi.trace(path: String, block: Operation.() -> Operation): Openapi {
    initiatePath(path)
    this.paths[path]?.trace = block(Operation())
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************* Operation *******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *tags* field of a [Operation] object.
 *
 * **Runs in the context of a [Operation] object.**
 */
infix fun Operation.tags(tags: Array<String>): Operation {
    this.tags = tags.toMutableList()
    return this
}

/**
 * Sets the the *summary* field of a [Operation] object.
 *
 * **Runs in the context of a [Operation] object.**
 */
infix fun Operation.summary(summary: String): Operation {
    this.summary = summary
    return this
}

/**
 * Sets the the *description* field of a [Operation] object.
 *
 * **Runs in the context of a [Operation] object.**
 */
infix fun Operation.description(description: String): Operation {
    this.description = description
    return this
}

/**
 * Sets the the *externalDocs* field of a [Operation] object.
 *
 * **Runs in the context of a [Operation] object.**
 */
fun Operation.externalDocs(block: ExternalDocumentation.() -> ExternalDocumentation): Operation {
    this.externalDocs = block(ExternalDocumentation(url = ""))
    return this
}

/**
 * Sets the the *operationId* field of a [Operation] object.
 *
 * **Runs in the context of a [Operation] object.**
 */
infix fun Operation.operationId(operationId: String): Operation {
    this.operationId = operationId
    return this
}

/**
 * Adds a [Pair] of [String] and a [Parameter] object of type **query**
 * to the **parameters** [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Parameter] object.
 */
fun Operation.query(name: String, block: Parameter.() -> Parameter): Operation {
    initiateParameters()
    this.parameters?.add(block(Parameter(`in` = QUERY, name = name)))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Parameter] object of type **header**
 * to the **parameters** [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Parameter] object.
 */
fun Operation.header(name: String, block: Parameter.() -> Parameter): Operation {
    initiateParameters()
    this.parameters?.add(block(Parameter(`in` = HEADER, name = name)))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Parameter] object of type **path**
 * to the **parameters** [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Parameter] object.
 */
fun Operation.path(name: String, block: Parameter.() -> Parameter): Operation {
    initiateParameters()
    this.parameters?.add(block(Parameter(`in` = PATH, name = name)))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Parameter] object of type **cookie**
 * to the **parameters** [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Parameter] object.
 */
fun Operation.cookie(name: String, block: Parameter.() -> Parameter): Operation {
    initiateParameters()
    this.parameters?.add(block(Parameter(`in` = COOKIE, name = name)))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Response] object to the **responses**
 * [Map] of an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [statusCode] The http status code.
 * @param [block] A function that constructs a [Response] object.
 */
fun Operation.respondWith(statusCode: String?, block: Response.() -> Response): Operation {
    this.responses[statusCode ?: "default"] = block(Response(description = ""))
    return this
}

/**
 * Sets the the *requestBody* field of a [RequestBody] object.
 *
 * **Runs in the context of a [RequestBody] object.**
 */
fun Operation.body(block: RequestBody.() -> RequestBody): Operation {
    this.requestBody = block(RequestBody(content = mutableMapOf()))
    return this
}

/**
 * Adds a [Pair] of [String] and a [Callback] object to
 * the **callbacks** [Map] with in the *components* element of an
 * [Openapi] object.
 *
 * **Runs in the context of an [Openapi] object.**
 *
 * @param [key] The name of the key.
 * @param [block] A function that constructs a [Callback] object.
 */
fun Operation.callback (key: String, block: Callback.() -> Callback): Operation {
    if(this.callbacks === null) {
        this.callbacks = mutableMapOf(Pair(key, mutableMapOf()))
    }

    this.callbacks?.put(key, this.callbacks!![key]!!)
    return this
}

/**
 * Sets the the *deprecated* field of a [Operation] object.
 *
 * **Runs in the context of a [Operation] object.**
 */
infix fun Operation.deprecated(deprecated: Boolean): Operation {
    this.deprecated = deprecated
    return this
}

/**
 * Sets the the *security* field of a [Operation] object.
 *
 * **Runs in the context of a [Operation] object.**
 */
fun Operation.securityRequirement(vararg securityRequirement: Pair<String, Array<String>>): Operation {
    this.security = mutableMapOf(*securityRequirement)
    return this
}

/**
 * Adds a [Server] object to the *servers* [List] in an [Operation] object.
 *
 * **Runs in the context of an [Operation] object.**
 *
 * @param [block] A function that constructs a [Server] object.
 */
fun Operation.server(block: Server.() -> Server): Operation {
    if (this.servers == null) {
        this.servers = mutableListOf()
    }

    this.servers?.add(block(Server("")))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** MediaType ******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *schema* field of a [MediaType] object.
 *
 * **Runs in the context of a [MediaType] object.**
 */
fun MediaType.schema(block: Schema.() -> Schema): MediaType {
    this.schema = block(Schema(type = themis.types.swagger.String))
    return this
}

/**
 * Sets the the *example* field of a [MediaType] object.
 *
 * **Runs in the context of a [MediaType] object.**
 */
infix fun MediaType.example(example: Any): MediaType {
    this.example = example
    return this
}

/**
 * Adds an [Example] object to the *examples* [Map] in an [MediaType] object.
 *
 * **Runs in the context of an [MediaType] object.**
 *
 * @param [block] A function that constructs an [Example] object.
 */
fun MediaType.example(key: String, block: Example.() -> Example): MediaType {
    if (this.examples == null) {
        this.examples = mutableMapOf()
    }

    this.examples?.put(key, block(Example()))
    return this
}

/**
 * Adds an [Encoding] object to the *examples* [Map] in an [MediaType] object.
 *
 * **Runs in the context of an [MediaType] object.**
 *
 * @param [block] A function that constructs an [Encoding] object.
 */
fun MediaType.encoding(key: String, block: Encoding.() -> Encoding): MediaType {
    if (this.encoding == null) {
        this.encoding = mutableMapOf()
    }

    this.encoding?.put(key, block(Encoding()))
    return this
}

/*-----------------------------------------------------------------------------------------------------------------------------------------*/
/************************************************************** Encoding *******************************************************************/
/*-----------------------------------------------------------------------------------------------------------------------------------------*/

/**
 * Sets the the *contentType* field of a [Encoding] object.
 *
 * **Runs in the context of a [Encoding] object.**
 */
fun Encoding.contentType(type: String): Encoding {
    this.contentType = type
    return this
}

/**
 * Adds an [Header] object to the *examples* [Map] in an [Encoding] object.
 *
 * **Runs in the context of an [Encoding] object.**
 *
 * @param [block] A function that constructs an [Header] object.
 */
fun Encoding.header(key: String, block: Header.() -> Header): Encoding {
    if (this.headers == null) {
        this.headers = mutableMapOf()
    }

    this.headers?.put(key, block(Header()))
    return this
}

/**
 * Sets the the *style* field of a [Encoding] object.
 *
 * **Runs in the context of a [Encoding] object.**
 */
fun Encoding.style(style: String): Encoding {
    this.style = style
    return this
}

/**
 * Sets the the *explode* field of a [Encoding] object.
 *
 * **Runs in the context of a [Encoding] object.**
 */
fun Encoding.explode(explode: Boolean): Encoding {
    this.explode = explode
    return this
}

/**
 * Sets the the *allowReserved* field of a [Encoding] object.
 *
 * **Runs in the context of a [Encoding] object.**
 */
fun Encoding.allowReserved(allowReserved: Boolean): Encoding {
    this.allowReserved = allowReserved
    return this
}
