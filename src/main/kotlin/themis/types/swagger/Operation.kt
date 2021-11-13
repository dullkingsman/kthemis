package themis.types.swagger

/**
 * Describes a single API operation on a path.
 */
data class Operation(
  /**
   * A list of tags for API documentation control. Tags can be used for
   * logical grouping of operations by resources or any other qualifier.
   */
  var tags: MutableList<String>? = null,
  /**
   * A short summary of what the operation does.
   */
  var summary: String? = null,
  /**
   * A verbose explanation of the operation behavior.
   * [CommonMark syntax](https://spec.commonmark.org/)
   * MAY be used for rich text representation.
   */
  var description: String? = null,
  /**
   * Additional external documentation for this operation.
   */
  var externalDocs: ExternalDocumentation? = null,
  /**
   * Unique string used to identify the operation. The id MUST be unique
   * among all operations described in the API. The operationId value is
   * case-sensitive. Tools and libraries MAY use the operationId to
   * uniquely identify an operation, therefore, it is RECOMMENDED to
   * follow common programming naming conventions.
   */
  var operationId: String? = null,
  /**
   * A list of parameters that are applicable for this operation. If a
   * parameter is already defined at the Path Item, the new definition
   * will override it but can never remove it. The list MUST NOT include
   * duplicated parameters. A unique parameter is defined by a
   * combination of a name and location. The list can use the Reference
   * Object to link to parameters that are defined at the OpenAPI
   * Object's components/parameters.
   */
  var parameters: MutableList<Parameter>? = null,
  /**
   * The request body applicable for this operation. The `requestBody`
   * is only supported in HTTP methods where the HTTP 1.1 specification
   * [RFC7231](https://tools.ietf.org/html/rfc7231#section-4.3.1) has
   * explicitly defined semantics for request bodies. In
   * other cases where the HTTP spec is vague, `requestBody` SHALL be
   * ignored by consumers.
   */
  var requestBody: RequestBody? = null,
  /**
   * The list of possible responses as they are returned from executing
   * this operation.
   *
   * It maps a HTTP response code to the expected response.
   *
   * The documentation is not necessarily expected to cover all possible
   * HTTP response codes because they may not be known in advance. However,
   * documentation is expected to cover a successful operation response
   * and any known errors.
   *
   * The `default` MAY be used as a default [Response] object for all HTTP
   * codes that are not covered individually by the specification.
   *
   * This map MUST contain at least one response code, and it
   * SHOULD be the response for a successful operation call.
   */
  var responses: MutableMap<String, Response> = mutableMapOf(),
  /**
   * A map of possible out-of band callbacks related to the parent
   * operation. The key is a unique identifier for the
   * [Callback] object. Each value in the map is a [Callback]
   * object that describes a request that may be initiated by the API
   * provider and the expected responses.
   */
  var callbacks: MutableMap<String, Callback>? = null,
  /**
   * Declares this operation to be deprecated. Consumers SHOULD refrain
   * from usage of the declared operation. Default value is `false`.
   */
  var deprecated: Boolean? = null,
  /**
   * A declaration of which security mechanisms can be used for this
   * operation. The list of values includes alternative security
   * requirement pairs that can be used. Only one of the security
   * requirement pairs need to be satisfied to authorize a request.
   * To make security optional, an empty security requirement (`{}`) can
   * be included in the array. This definition overrides any declared
   * top-level security. To remove a top-level security declaration, an
   * empty array can be used.
   */
  var security: SecurityRequirement? = null,
  /**
   * An alternative [Server] array to service this operation. If an
   * alternative [Server] object is specified at the [PathsItem] object or
   * Root level, it will be overridden by this value.
   */
  var servers: MutableList<Server>? = null,
) { val o = this }