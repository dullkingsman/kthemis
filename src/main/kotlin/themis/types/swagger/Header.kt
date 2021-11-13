package themis.types.swagger

/**
 * The [Header] object follows the structure of the [Parameter] object
 * with slight variation.
 */
data class Header(
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