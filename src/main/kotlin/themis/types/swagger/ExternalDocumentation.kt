package themis.types.swagger

import kotlin.String

/**
 * Allows referencing an external resource for extended documentation.
 */
data class ExternalDocumentation (
    /**
     * A short description of the target documentation.
     * [CommonMark syntax](https://spec.commonmark.org/)  MAY be used for rich text
     * representation.
     */
    var description: String? = null,
    /**
     * The URL for the target documentation. Value MUST be in the format
     * of a URL.
     */
    var url: String
) { val o = this }