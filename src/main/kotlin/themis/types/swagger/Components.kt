package themis.types.swagger

import kotlin.String

/**
 * Holds a set of reusable objects for different aspects of the OAS.
 * All objects defined within the [Components] object will have no effect
 * on the API unless they are explicitly referenced from properties
 * outside the [Components] object.
 *
 * All the fixed fields declared above are objects that MUST use keys
 * that match the regular expression: `^[a-zA-Z0-9\.\-_]+$`.
 */
data class Components(
	/**
	 * An object to hold reusable [Schema] objects.
	 */
	var schemas: MutableMap<String, Schema>? = null,
//	/**
//	 * An object to hold reusable [Response] objects.
//	 */
//	val responses: MutableMap<String, ResponseObject>? = null,
//	/**
//	 * An object to hold reusable [Parameter] objects.
//	 */
//	val parameters: MutableMap<String, ParameterObject>? = null,
	/**
	 * An object to hold reusable [Example] objects.
	 */
	var examples: MutableMap<String, Example>? = null,
//	/**
//	 * An object to hold reusable [RequestBody] objects.
//	 */
//	val requestBodies: MutableMap<String, RequestBodyObject>? = null,
//	/**
//	 * An object to hold reusable [Header] objects.
//	 */
//	val headers: MutableMap<String, HeaderObject>? = null,
//	/**
//	 * An object to hold reusable [SecurityScheme] objects.
//	 */
//	val securitySchemes: MutableMap<String, SecuritySchemeObject>? = null,
//	/**
//	 * An object to hold reusable [Link] objects.
//	 */
//	val links: MutableMap<String, LinkObject>? = null,
//	/**
//	 * An object to hold reusable [Callback] objects.
//	 */
//	val callbacks: MutableMap<String, CallbackObject>? = null
) { val o = this }