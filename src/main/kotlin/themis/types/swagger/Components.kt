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
	/**
	 * An object to hold reusable [Response] objects.
	 */
	var responses: MutableMap<String, Response>? = null,
	/**
	 * An object to hold reusable [Parameter] objects.
	 */
	var parameters: MutableMap<String, Parameter>? = null,
	/**
	 * An object to hold reusable [Example] objects.
	 */
	var examples: MutableMap<String, Example>? = null,
	/**
	 * An object to hold reusable [RequestBody] objects.
	 */
	var requestBodies: MutableMap<String, RequestBody>? = null,
	/**
	 * An object to hold reusable [Header] objects.
	 */
	var headers: MutableMap<String, Header>? = null,
	/**
	 * An object to hold reusable [SecurityScheme] objects.
	 */
	var securitySchemes: MutableMap<String, SecurityScheme>? = null,
	/**
	 * An object to hold reusable [Link] objects.
	 */
	var links: MutableMap<String, Link>? = null,
	/**
	 * An object to hold reusable <[String],[PathsItem]> pairs.
	 */
	var callbacks: MutableMap<String, Callback>? = null
) { val o = this }