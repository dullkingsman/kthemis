package themis.types.swagger

import kotlin.String

/**
 * Adds metadata to a single tag that is used by the
 * OperationObject.
 *
 * Note: It is not mandatory to have a [Tag] object per
 * tag defined in the [Operation] object instances.
 */
data class Tag (
    /**
     * The name of the tag.
     */
    var name: String,
    /**
     * A short description for the tag.
     * [CommonMark syntax](https://spec.commonmark.org/) MAY be used
     * for rich text representation.
     */
    var description: String? = null,
    /**
     * Additional external documentation for this tag.
     */
    var externalDocs: ExternalDocumentation? = null
) { val o = this }