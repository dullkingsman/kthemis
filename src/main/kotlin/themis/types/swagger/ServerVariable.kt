package themis.types.swagger

import kotlin.String

/**
 * An object representing a Server Variable for server URL template
 * substitution.
 */
data class ServerVariable(
    /**
     * An enumeration of string values to be used if the
     * substitution options are from a limited set. The array
     * SHOULD NOT be empty.
     */
    var enum: MutableList<String>? = null,
    /**
     * The default value to use for substitution, which SHALL
     * be sent if an alternate value is not supplied. If the `enum`
     * is defined, the value SHOULD exist in the enum's values.
     */
    var default: String,
    /**
     * An optional description for the server variable.
     * [CommonMark syntax](https://spec.commonmark.org/) MAY be used for rich
     * text representation.
     */
    var description: String? = null
) { val o = this }