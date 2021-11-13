package themis.types.swagger

/**
 * Each [MediaType] object provides schema and examples for the media
 * type identified by its key.
 */
data class MediaType(
  /**
   * The schema defining the content of the request, response,
   * or parameter.
   */
  var schema: Schema? = null,
  /**
   * Example of the media type. This object SHOULD be in
   * the correct format as specified by the media type. The
   * `example` field is mutually exclusive of the `examples` field.
   * Furthermore, if referencing a `schema` which contains an
   * example, the `example` value SHALL override the example
   * provided by the schema.
   */
  var example: Any? = null,
  /**
   * Examples of the media type. Each [Example] object SHOULD match
   * the media type and specified schema if present. The `examples`
   * field is mutually exclusive of the `example` field.
   * Furthermore, if referencing a schema which contains an
   * example, the `examples` value SHALL override the example
   * provided by the schema.
   */
  var examples: MutableMap<String, Example>? = null,
  /**
   * A map between a property name and its encoding information.
   * The key, being the property name, MUST exist in the schema
   * as a property. The [Encoding] object SHALL only apply to
   * [RequestBody] objects when the media type is `multipart` or
   * `application/x-www-form-urlencoded`.
   */
  var encoding: MutableMap<String, Encoding>? = null,
) { val o = this }