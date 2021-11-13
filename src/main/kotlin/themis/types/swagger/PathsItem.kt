package themis.types.swagger

/**
 * Describes the operations available on a single path. A Path Item
 * MAY be empty, due to ACL constraints. The path itself is still
 * exposed to the documentation viewer but they will not know which
 * operations and parameters are available.
 */
data class PathsItem(
  /**
   * Allows for an external definition of this path item. The
   * referenced structure MUST be in the format of a [PathsItem]
   * object. In case a [PathsItem] object field appears both in the
   * defined object and the referenced object, the behavior is
   * undefined.
   */
  var `$ref`: String? = null,
  /**
   * An optional, string summary, intended to apply to all
   * operations in this path.
   */
  var summary: String? = null,
  /**
   * An optional, string description, intended to apply to all
   * operations in this path. [CommonMark
     * syntax](https://spec.commonmark.org/) MAY be used for
   * rich text representation.
   */
  var description: String? = null,
  /**
   * A definition of a GET operation on this path.
   */
  var get: Operation? = null,
  /**
   * A definition of a PUT operation on this path.
   */
  var put: Operation? = null,
  /**
   * A definition of a POST operation on this path.
   */
  var post: Operation? = null,
  /**
   * A definition of a DELETE operation on this path.
   */
  var delete: Operation? = null,
  /**
   * A definition of a OPTIONS operation on this path.
   */
  var options: Operation? = null,
  /**
   * A definition of a HEAD operation on this path.
   */
  var head: Operation? = null,
  /**
   * A definition of a PATCH operation on this path.
   */
  var patch: Operation? = null,
  /**
   * A definition of a TRACE operation on this path.
   */
  var trace: Operation? = null,
  /**
   * An alternative `server` array to service all operations
   * in this path.
   */
  var servers: MutableList<Server>? = null,
  /**
   * A list of parameters that are applicable for all the operations
   * described under this path. These parameters can be overridden
   * at the operation level, but cannot be removed there. The list
   * MUST NOT include duplicated parameters. A unique parameter is
   * defined by a combination of a name and location. The list can
   * use the Reference Object to link to parameters that are defined
   * at the *parameters* field on the components element of the
   * [Openapi] object.
   */
  var parameters: MutableList<Parameter>? = null,
) { val o = this }