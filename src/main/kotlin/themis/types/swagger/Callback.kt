package themis.types.swagger

/**
 * A map of possible out-of band callbacks related to the parent operation.
 * Each value in the map is a [PathsItem] object that describes a set of
 * requests that may be initiated by the API provider and the expected
 * responses. The key value used to identify the [PathsItem] object is an
 * expression, evaluated at runtime, that identifies a URL to use for the
 * callback operation.
 *
 * The key that identifies the [PathsItem] object is a runtime expression
 * that can be evaluated in the context of a runtime HTTP request/response
 * to identify the URL to be used for the callback request. A simple
 * example might be `$request.body#/url`.
 *
 * A [complete example](https://github.com/OAI/OpenAPI-Specification/blob/main/examples/v3.0/callback-example.yaml)
 * is available.
 */
typealias Callback = MutableMap<String, PathsItem>