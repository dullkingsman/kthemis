package themis.types.swagger

import kotlin.String

/**
 * The object provides metadata about the API. The metadata MAY
 * be used by the clients if needed, and MAY be presented in
 * editing or documentation generation tools for convenience.
 */
data class Info(
	/**
     * The title of the API.
     */
	var title: kotlin.String,
	/**
     * A short description of the API.
     * [CommonMark syntax](https://spec.commonmark.org/) MAY be
     * used for rich text representation.
     */
	var description: String? = null,
	/**
     * A URL to the Terms of Service for the API. MUST be in the
     * format of a URL.
     */
	var termsOfService: String? = null,
	/**
     * The contact information for the exposed API.
     */
	val contact: Contact? = null,
	/**
     * The license information for the exposed API.
     */
	val license: License? = null,
	/**
     * The version of the OpenAPI document (which is distinct from
     * the [OpenAPI Specification version](https://github.com/OAI/OpenAPI-Specification/blob/main/versions/3.0.3.md#oasVersion)
     * or the API implementation version).
     */
	var version: kotlin.String
) { val o = this }