package example

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import themis.SpecList

class ThemisExampleTest {
  @Test
  fun describe () {
    val example = ThemisExample()

    val expected = "{\"openapi\":\"3.0.0\",\"info\":{\"title\":\"BillTheBoard API\",\"description\":\"An API to serve BillTheBoard.\",\"version\":\"0.0.1\"},\"servers\":[{\"url\":\"{host}:{port}/{apiVersion}\",\"description\":\"Development Server\",\"variables\":{\"host\":{\"default\":\"http://localhost\"},\"port\":{\"enum\":[\"3000\",\"443\"],\"default\":\"3000\"},\"apiVersion\":{\"default\":\"v2\"}}}],\"paths\":{},\"components\":{\"schemas\":{\"profile\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"string\",\"format\":\"uuid\"},\"firstName\":{\"type\":\"string\"},\"dateOfBirth\":{\"type\":\"string\",\"format\":\"date-time\"}}},\"tile\":{\"type\":\"object\",\"properties\":{\"lime\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}},\"array\":{\"type\":\"object\",\"properties\":{\"of\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"objects\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}}}}}},\"space\":{\"type\":\"object\",\"properties\":{\"time\":{\"type\":\"object\",\"properties\":{\"reality\":{\"type\":\"string\"}}}}},\"tor\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}},\"single\":{\"type\":\"number\"}},\"examples\":{\"profileSignUp\":{\"value\":\"{\\n\\t\\\"id\\\": \\\"3243ff-fdaf434-43344-4323\\\",\\n\\t\\\"firstName\\\": \\\"Lima\\\",\\n\\t\\\"dateOfBirth\\\": Wed Nov 10 19:50:04 EAT 2021\\\",\\n}\"}},\"links\":{\"tied\":{\"operationRef\":\"Operation reference\",\"operationId\":\"Operation Id\",\"parameters\":{\"someParam\":\"some value\",\"someOtherParam\":\"some other value\"},\"requestBody\":\"Some value\",\"description\":\"Link description\",\"server\":{\"url\":\"https://remotehost:32439\"}}}},\"tags\":[{\"name\":\"Profile\",\"description\":\"Profile control section.\"},{\"name\":\"Auth\",\"description\":\"API access section.\"}],\"externalDocs\":{\"description\":\"Just an example.\",\"url\":\"https://remotehost:45544\"}}"

    example.describe("v1")

    assertEquals(expected, Gson().toJson(SpecList.specs["v1"]))
  }
}