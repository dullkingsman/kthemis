package themis.types.swagger

/**
 * Describes a single operation parameter.
 *
 * A unique parameter is defined by a combination of a `name` and
 * `location`.
 * ___
 * ### Parameter Locations
 * There are four possible parameter locations specified by the `in`
 * field:
 * * *path* - Used together with `Path Templating`, where the parameter
 *   value is actually part of the operation's URL. This does not
 *   include the host or base path of the API. For example, in
 *   `/items/{itemId}`, the path parameter is `itemId`.
 * * *query* - Parameters that are appended to the URL. For example, in
 *   `/items?id=###`, the query parameter is `id`.
 * * *header* - Custom headers that are expected as part of the request.
 *   Note that [RFC7230](https://tools.ietf.org/html/rfc7230#page-22)
 *   states header names are case insensitive.
 * * *cookie* - Used to pass a specific cookie value to the API.
 *
 * The rules for serialization of the parameter are specified in one
 * of two ways. For simpler scenarios, a `schema` and `style` can describe
 * the structure and syntax of the parameter.
 *
 * For more complex scenarios, the `content` property can define the
 * media type and schema of the parameter. A parameter MUST contain
 * either a `schema` property, or a `content` property, but not both. When
 * `example` or `examples` are provided in conjunction with the [Schema]
 * object, the example MUST follow the prescribed serialization
 * strategy for the parameter.
 */
data class Parameter(
  /**
   * The name of the parameter. Parameter names are case sensitive.
   * * If `in` is `"path"`, the `name` field MUST correspond to a
   *   template expression occurring within the path key in the
   *   *paths* element of the [Openapi] object.
   * * If `in` is `"header"` and the `name` field is `"Accept"`,
   *   `"Content-Type"` or `"Authorization"`, the parameter
   *   definition SHALL be ignored.
   * * For all other cases, the `name` corresponds to the parameter
   *   name used by the `in` property.
   */
  var name: String,
  /**
   * The location of the parameter. Possible values are `"query"`,
   * `"header"`, `"path"` or `"cookie"`.
   */
  var `in`: String,
  /**
   * A brief description of the parameter. This could contain
   * examples of use.
   * [CommonMark syntax](https://spec.commonmark.org/) MAY be used
   * for rich text representation.
   */
  var description: String? = null,
  /**
   * Determines whether this parameter is mandatory. Default value
   * is *`false`*.
   * ___
   * **REQUIRED** if the parameter location is *`"path"`* (the value
   * must be *`true`*).
   */
  var required: Boolean? = null,
  /**
   * Specifies that a parameter is deprecated and SHOULD be
   * transitioned out of usage. Default value is `false`.
   */
  var deprecated: Boolean? = null,
  /**
   * Sets the ability to pass empty-valued parameters. This is valid
   * only for query parameters and allows sending a parameter with
   * an empty value. Default value is `false`. If `style` is used, and
   * if behavior is `n/a` (cannot be serialized), the value of
   * `allowEmptyValue` SHALL be ignored. Use of this property is NOT
   * RECOMMENDED, as it is likely to be removed in a later revision.
   */
  var allowEmptyValue: Boolean? = null,
  /**
   * Describes how the parameter value will be serialized depending
   * on the type of the parameter value. Default values (based on
   * value of `in`): for `query` - `form`; for `path` - `simple`; for
   * `header` - `simple`; for `cookie` - `form`.
   */
  var style: String? = null,
  /**
   * When this is true, parameter values of type `array` or
   * `object` generate separate parameters for each value of the
   * array or key-value pair of the map. For other themis of
   * parameters this property has no effect. When `style` is `form`,
   * the default value is `true`. For all other styles, the default
   * value is `false`.
   */
  var explode: Boolean? = null,
  /**
   * Determines whether the parameter value SHOULD allow reserved
   * characters, as defined by
   * [RFC3986](https://tools.ietf.org/html/rfc3986#section-2.2)
   * `:/?#[]@!$&'()*+,;=` to be included without percent-encoding.
   * This property only applies to parameters with an `in` value
   * of `query`. The default value is `false`.
   */
  var allowReserved: Boolean? = null,
  /**
   * The schema defining the type used for the parameter.
   */
  var schema: Schema? = null,
  /**
   * Example of the parameter's potential value. The example
   * SHOULD match the specified schema and encoding properties if
   * present. The `example` field is mutually exclusive of the
   * `examples` field. Furthermore, if referencing a `schema` that
   * contains an example, the `example` value SHALL override the
   * example provided by the schema. To represent examples of media
   * themis that cannot naturally be represented in JSON or YAML, a
   * string value can contain the example with escaping where
   * necessary.
   */
  var example: Any? =  null,
  /**
   * Examples of the parameter's potential value. Each example
   * SHOULD contain a value in the correct format as specified in
   * the parameter encoding. The `examples` field is mutually
   * exclusive of the `example` field. Furthermore, if referencing
   * a `schema` that contains an example, the `examples` value SHALL
   * override the example provided by the schema.
   */
  var examples: MutableMap<String, Example>? = null,
  /**
   * A map containing the representations for the parameter. The key
   * is the media type and the value describes it. The map MUST only
   * contain one entry.
   */
  var content: MutableMap<String, MediaType>? = null
) { val o = this }