package themis.types.swagger

/**
 * Allows configuration of the supported OAuth Flows.
 */
data class OAuthFlows(
  /**
   * Configuration for the OAuth Implicit flow
   */
  var implicit: OAuthFlow? = null,
  /**
   * Configuration for the OAuth Resource Owner Password flow
   */
  var password: OAuthFlow? = null,
  /**
   * Configuration for the OAuth Client Credentials flow.
   * Previously called `application` in OpenAPI 2.0.
   */
  var clientCredentials: OAuthFlow? = null,
  /**
   * Configuration for the OAuth Authorization Code flow.
   * Previously called `accessCode` in OpenAPI 2.0.
   */
  var authorizationCode: OAuthFlow? = null,
) { val o = this }