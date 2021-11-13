package themis.types.swagger

/**
 * Describes a single response from an API Operation, including
 * design-time, static `links` to operations based on the response.
 */
data class Response(
  /**
   * A short description of the response.
   * [CommonMark syntax](https://spec.commonmark.org/) MAY be used
   * for rich text representation.
   */
  var description: String,
  /**
   * Maps a header name to its definition.
   * [RFC7230](https://tools.ietf.org/html/rfc7230#page-22) states
   * header names are case insensitive. If a response header is
   * defined with the name `"Content-Type"`, it SHALL be ignored.
   */
  var headers: MutableMap<String, Header>? = null,
  /**
   * A map containing descriptions of potential response payloads.
   * The key is a media type or
   * [media type range](https://tools.ietf.org/html/rfc7231#appendix-D)
   * and the value describes it. For responses that match multiple keys,
   * only the most specific key is applicable.
   * e.g. text/plain overrides text/`*`
  */
  var content: MutableMap<String, MediaType>? = null,
  /**
   * A map of operations links that can be followed from the response.
   * The key of the map is a short name for the link, following the
   * naming constraints of the names for [Components].
  */
  var links: MutableMap<String, Link>? = null,
) { val o = this }