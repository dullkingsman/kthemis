package themis.types.swagger

import kotlin.String

/**
 * This object allows the definition of input and output data
 * types. These types can be objects, but also primitives and arrays.
 * This object is an extended subset of the
 * [JSON Schema Specification Wright Draft 00](https://json-schema.org/).
 *
 * For more information about the properties, see [JSON Schema Core](https://tools.ietf.org/html/draft-wright-json-schema-00) and
 * [JSON Schema Validation](https://tools.ietf.org/html/draft-wright-json-schema-validation-00).
 * Unless stated otherwise, the property definitions follow the JSON Schema.
 *
 * Additional properties defined by the JSON Schema specification that
 * are not mentioned here are strictly unsupported.
 */
open class Schema(
	/**
	 * Short information about the data.
	 */
	var title: String? = null,
	/**
	 * An extended description of the schema and its contents.
	 * [CommonMark syntax](https://spec.commonmark.org/) MAY
	 * be used for rich text representation.
	 */
	var description: String? = null,
	/**
	 * A number strictly greater than 0 which tells that a
	 * numeric instance is valid only if division by this
	 * keyword's value results in an integer.
	 */
	var multipleOf: Number? = null,
	/**
	 * An inclusive upper limit for a numeric instance.
	 */
	var maximum: Number? = null,
	/**
	 * An exclusive upper limit for a numeric instance.
	 */
	var exclusiveMaximum: Number? = null,
	/**
	 * An inclusive lower limit for a numeric instance.
	 */
	var minimum: Number? = null,
	/**
	 * An exclusive lower limit for a numeric instance.
	 */
	var exclusiveMinimum: Number? = null,
	/**
	 * A non-negative integer value that is an inclusive
	 * upper limit for the length of a string instance.
	 */
	var maxLength: Number? = null,
	/**
	 * A non-negative integer value that is an inclusive
	 * lower limit for the length of a string instance.
	 */
	var minLength: Number? = null,
	/**
	 * A non-negative integer value that is an inclusive
	 * upper limit for the size of an array instance.
	 */
	var maxItems: Number? = null,
	/**
	 * A non-negative integer value that is an inclusive
	 * lower limit for the size of an array instance.
	 */
	var minItems: Number? = null,
	/**
	 * Value indicating that all elements of the schema
	 * MUST be unique.
	 */
	var uniqueItems: Boolean? = null,
	/**
	 * A non-negative integer value that is an inclusive
	 * upper limit for the number of properties with in
	 * an object instance.
	 */
	var maxProperties: Number? = null,
	/**
	 * A non-negative integer value that is an inclusive
	 * lower limit for the number of properties with in
	 * an object instance.
	 */
	var minProperties: Number? = null,
	/**
	 * A list of unique strings that mark certain properties
	 * of the schema as required fields.
	 */
	var required: MutableList<String>? = null,
	/**
	 * A definition for the values of an enumerable.
	 */
	var enum: MutableList<Any>? = null,
	/**
	 * A valid regular expression as defined by
	 * [Ecma-262 Edition 5.1 regular expression dialect](https://www.ecma-international.org/ecma-262/5.1/#sec-15.10.1),
	 * which is used to validate a string instance.
	 */
	var pattern: String? = null,
	/**
	 * The data type of the schema to be defined.
	 */
	var type: String,
	/**
	 * Specifies a type validation that requires the
	 * schema to be valid against all specified
	 * schemas.
	 */
	var allOf: MutableList<Schema>? = null,
	/**
	 * Specifies a type validation that requires the
	 * schema to be valid against only one of the
	 * specified schemas.
	 */
	var oneOf: MutableList<Schema>? = null,
	/**
	 * Specifies a type validation that requires the
	 * schema to be valid against at least one of the
	 * specified schemas.
	 */
	var anyOf: MutableList<Schema>? = null,
	/**
	 * Specifies a negating type validation.
	 */
	var not: Schema? = null,
	/**
	 * Specifies the schema of a single array instance.
	 */
	var items: Schema? = null,
	/**
	 * Specifies the properties that the schema contains.
	 */
	var properties: MutableMap<String, Schema>? = null,
	/**
	 * Specifies the properties that a child instance of
	 * the schema contains.
	 */
	var additionalProperties: Schema? = null/* | Boolean*/,
	/**
	 * The format in which the data is represented. This is a sub-type
	 * categorization.
	 */
	var format: String? = null,
	/**
	 * The default value represents what would be assumed by the
	 * consumer of the input as the value of the schema if one is not
	 * provided. Unlike JSON Schema, the value MUST conform to the
	 * defined type for the [Schema] object defined at the same level.
	 * For example, if `type` is `string`, then `default` can be `"foo"` but
	 * cannot be `1`.
	 */
	var default: Any? = null,
	/**
	 * A `true` value adds `"null"` to the allowed type specified by the
	 * `type` keyword, only if `type` is explicitly defined within the
	 * same [Schema] object. Other [Schema] object constraints retain
	 * their defined behavior, and therefore may disallow the use of
	 * `null` as a value. A `false` value leaves the specified or default
	 * `type` unmodified. The default value is `false`.
	 */
	var nullable: Boolean? = null,
//	/**
//	 * Adds support for polymorphism. The discriminator is an object
//	 * name that is used to differentiate between other schemas which
//	 * may satisfy the payload description.
//	 */
//	var discriminator: DiscriminatorObject? = null,
//	/**
//	 * Relevant only for Schema `"properties"` definitions. Declares
//	 * the property as "read only". This means that it MAY be sent as
//	 * part of a response but SHOULD NOT be sent as part of the request.
//	 * If the property is marked as `readOnly` being `true` and is in
//	 * the `required` list, the `required` will take effect on the
//	 * response only. A property MUST NOT be marked as both `readOnly`
//	 * and `writeOnly` being `true`. Default value is `false`.
//	 */
//	var readonly: Boolean? = null,
//	/**
//	 * Relevant only for Schema `"properties"` definitions. Declares
//	 * the property as `"write only"`. Therefore, it MAY be sent as
//	 * part of a request but SHOULD NOT be sent as part of the
//	 * response. If the property is marked as `writeOnly` being `true`
//	 * and is in the `required` list, the `required` will take effect on
//	 * the request only. A property MUST NOT be marked as both
//	 * `readOnly` and `writeOnly` being `true`. Default value is `false`.
//	 */
//	var writeOnly: Boolean? = null,
//	/**
//	 * This MAY be used only on properties schemas. It has no
//	 * effect on root schemas. Adds additional metadata to describe
//	 * the XML representation of this property.
//	 */
//	var xml: XMLObject? = null,
//	/**
//	 * Additional external documentation for this schema.
//	 */
//	var externalDocs: ExternalDocumentationObject? = null,
	/**
	 * A free-form property to include an example of an instance
	 * for this schema. To represent examples that cannot be
	 * naturally represented in JSON or YAML, a string value can
	 * be used to contain the example with escaping where necessary.
	 */
	var example: Any? = null,
	/**
	 * Specifies that a schema is deprecated and SHOULD be
	 * transitioned out of usage. Default value is *`false`*.
	 */
	var deprecated: Boolean? = null
) { val o = this }