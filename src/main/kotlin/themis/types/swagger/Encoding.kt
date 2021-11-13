package themis.types.swagger

/**
 * A single encoding definition applied to a single schema
 * property.
 */
data class Encoding(
  /**
   * The Content-Type for encoding a specific property.
   * Default value depends on the property type: for `string`
   * with `format` being `binary` – `application/octet-stream`;
   * for other primitive themis – `text/plain`; for `object` -
   * `application/json`; for `array` – the default is defined
   * based on the inner type. The value can be a specific media
   * type (e.g. application/json), a wildcard media type (e.g.
   * `image/`*``), or a comma-separated list of the two themis.
   */
  var contentType: String? = null,
  /**
   * A map allowing additional information to be provided as
   * headers, for example `Content-Disposition`. `Content-Type`
   * is described separately and SHALL be ignored in this
   * section. This property SHALL be ignored if the request
   * body media type is not a `multipart`.
  */
  var headers: MutableMap<String, Header>? = null,
  /**
   * Describes how a specific property value will be serialized
   * depending on its type. The behavior follows the same values
   * as `query` parameters, including default values. This
   * property SHALL be ignored if the request body media type is
   * not `application/x-www-form-urlencoded`.
  */
  var style: String? = null,
  /**
   * When this is true, property values of type `array` or `object`
   * generate separate parameters for each value of the array,
   * or key-value-pair of the map. For other themis of properties
   * this property has no effect. When `style` is `form`, the default
   * value is `true`. For all other styles, the default value is
   * `false`. This property SHALL be ignored if the request body
   * media type is not `application/x-www-form-urlencoded`.
  */
  var explode: Boolean? = null,
  /**
   * Determines whether the parameter value SHOULD allow reserved
   * characters, as defined by
   * [RFC3986](https://tools.ietf.org/html/rfc3986#section-2.2)
   * `:/?#[]@!$&'()*+,;=` to be included without percent-encoding.
   * The default value is `false`. This property SHALL be ignored
   * if the request body media type is not
   * `application/x-www-form-urlencoded`.
  */
  var allowReserved: Boolean? = null
) { val o = this }