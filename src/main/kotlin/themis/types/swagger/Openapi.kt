package themis.types.swagger

import kotlin.String

/**
 * This is the root document object of the OpenAPI document.
 */
data class Openapi(
    /**
     * This string MUST be the
     * [semantic version number](https://semver.org/spec/v2.0.0.html)
     * of the [OpenAPI Specification version](https://swagger.io/specification/#versions)
     * that the OpenAPI document uses. The `openapi` field SHOULD be
     * used by tooling specifications and clients to interpret the
     * OpenAPI document.
     */
    var openapi: String,
    /**
     * Provides metadata about the API. The metadata MAY be used by
     * tooling as required.
     */
    val info: Info,
    /**
     * An [List] of [Server] objects, which provide connectivity
     * information to a target server. If the `servers` property is not
     * provided, or is an empty array, the default value would be a
     * [Server] object with a url value of `/`
     */
    val servers: MutableList<Server> = mutableListOf<Server>(),
//    /**
//     * The available paths and operations for the API.
//     */
//    val paths: PathsObject,
    /**
     * An element to hold various schemas for the specification.
     */
    var components: Components? = null,
//    /**
//     * A declaration of which security mechanisms can be used across
//     * the API. The list of values includes alternative [SecurityRequirement]
//     * objects that can be used. Only one of the [SecurityRequirement]
//     * objects need to be satisfied to authorize a request.
//     * Individual operations can override this definition. To make
//     * security optional, an empty security requirement (`{}`) can be
//     * included in the array.
//     */
//    val security: MutableList<SecurityRequirementObject>? = mutableListOf<SecurityRequirementObject>(),
    /**
     * A list of tags used by the specification with additional
     * metadata. The order of the tags can be used to reflect on their
     * order by the parsing tools. Not all tags that are used by the
     * [Operation] object must be declared. The tags that are not
     * declared MAY be organized randomly or based on the tools' logic.
     * Each tag name in the list MUST be unique.
     */
    var tags: MutableList<Tag>? = null,
    /**
     * Additional external documentation.
     */
    var externalDocs: ExternalDocumentation? = null,
) { val o = this }