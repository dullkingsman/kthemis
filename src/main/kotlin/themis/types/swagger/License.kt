package themis.types.swagger

import kotlin.String

/**
 * License information for the exposed API.
 */
data class License(
    /**
     * The license name used for the API.
     */
    var name: String,
    /**
     * A URL to the license used for the API. MUST be in the format
     * of a URL.
     */
    var url: String? = null
) { val o = this }