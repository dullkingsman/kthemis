package themis.types.swagger

/**
 * Configuration details for a supported OAuth Flow
 */
data class OAuthFlow(
  /**
   * The authorization URL to be used for this flow.
   * This MUST be in the form of a URL.
   * ___
   * **APPLIES TO AND REQUIRED** for *`"oauth2" ("implicit",
   * "authorizationCode")`*
   */
  var authorizationUrl: String? = null,
  /**
   * The token URL to be used for this flow. This MUST be
   * in the form of a URL.
   * ___
   * **APPLIES AND REQUIRED** for *`"oauth2" ("password",
   * "clientCredentials", "authorizationCode")`*
   */
  var tokenUrl: String? = null,
  /**
   * The URL to be used for obtaining refresh tokens. This MUST
   * be in the form of a URL.
   * ___
   * **APPLIES** to *`"oauth2"`*
   */
  var refreshUrl: String? = null,
  /**
   * The available scopes for the OAuth2 security scheme. A map
   * between the scope name and a short description for it. The
   * map MAY be empty.
   * ___
   * **APPLIES** to and **REQUIRED** for *`"oauth2"`*
   */
  var scopes: MutableMap<String, String>? = null,
) { val o = this }