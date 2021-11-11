package example

import themis.*

class ThemisExample {
	enum class Port {
		E3000,
		E443
	}

	fun describe (version: String?) {
		version!! api {
			openapi( "3.0.0")
			title( "BillTheBoard API")
			description( "An API to serve BillTheBoard.")
			version( "0.0.1")

			server {
				description("Development Server")
				url("{host}:{port}/{apiVersion}")

				variable("host") {
					default("http://localhost")
				}

				variable("port") {
					enum(Port::class)
					default( "3000")
				}

				variable("apiVersion") {
					default("v2")
				}
			}

			externalDocs {
				description("Just an example.")
				url("https://remotehost:45544")
			}

			tag {
				name("Profile")
				description("Profile control section.")
			}

			tag {
				name("Auth")
				description("API access section.")
			}

			schema("profile") {
				Object (
					"id" - UUID,
					"firstName" - STRING,
					"dateOfBirth" - DATETIME
				)
			}

			schema("tile") {
				Object(
					"lime" - Array { STRING }
				)
			}

			schema("array") {
				Object(
					"of" - Array {
						Object (
							"objects" - Array { STRING }
						)
					}
				)
			}

			schema("space") {
				Object(
					"time" - Object (
						"reality" - STRING
					)
				)
			}

			schema("tor") {
				Array { STRING }
			}

			schema("single") { NUMBER }

			example("profileSignUp") {
				value(
					"""
				{
					"id": "3243ff-fdaf434-43344-4323",
					"firstName": "Lima",
					"dateOfBirth": Wed Nov 10 19:50:04 EAT 2021",
				}
				"""
				)
			}


		}
	}
}


