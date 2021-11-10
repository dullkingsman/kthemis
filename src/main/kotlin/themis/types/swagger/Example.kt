package themis.types.swagger

import kotlin.String

/**
 * In all cases, the example value is expected to be compatible with
 * the type schema of its associated value. Tooling implementations
 * MAY choose to validate compatibility automatically, and reject the
 * example value(s) if incompatible.
 */
data class Example(
	/**
	 * Short description for the example.
	 */
	var summary: String? = null,
	/**
	 * Long description for the example.
	 * [CommonMark syntax](https://spec.commonmark.org/) MAY be used for rich text
	 * representation.
	 */
	var description: String? = null,
	/**
	 * Embedded literal example. The `value` field and `externalValue`
	 * field are mutually exclusive. To represent examples of media
	 * themis that cannot naturally represented in JSON or YAML, use a
	 * string value to contain the example, escaping where necessary.
	 */
	var value: Any? = null,
	/**
	 * A URL that points to the literal example. This provides the
	 * capability to reference examples that cannot easily be included
	 * in JSON or YAML documents. The `value` field and `externalValue`
	 * field are mutually exclusive.
	 */
	var externalValue: String? = null
) { val o = this }