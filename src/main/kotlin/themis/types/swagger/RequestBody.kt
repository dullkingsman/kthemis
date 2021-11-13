package themis.types.swagger

/**
 * Describes a single request body.
 */
data class RequestBody(
  /**
   * A brief description of the request body. This could contain
   * examples of use. [CommonMark
   * syntax](https://spec.commonmark.org/) MAY be used for rich
   * text representation.
   */
  var description: String? = null,
  /**
   * The content of the request body. The key is a media type or
   * [media type range](https://tools.ietf.org/html/rfc7231#appendix-D)
   * and the value describes it. For requests that match multiple keys,
   * only the most specific key is applicable.
   * e.g. text/plain overrides text/`*`
  */
  var content: MutableMap<String, MediaType> = mutableMapOf(),
  /**
   * Determines if the request body is required in the request.
   * Defaults to `false`.
  */
  var required: Boolean? = null
) { val o = this }