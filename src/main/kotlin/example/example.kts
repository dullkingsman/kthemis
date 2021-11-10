package example

import themis.*

enum class Port {
	E3000,
	E443
}

"v2" api {
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
			default( "3000" or env("PORT"))
		}

		variable("apiVersion") {
			default("v2")
		}
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
					"dateOfBirth": ${java.util.Date.from(java.time.Instant.now())}",
				}
				"""
		)
	}
}


