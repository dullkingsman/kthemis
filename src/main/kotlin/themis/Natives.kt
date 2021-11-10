package themis

import themis.types.swagger.*

object NUMBER: Schema(type = Number)
object INT: Schema(type = themis.types.swagger.Int)
object INT32: Schema(type = Number, format = Int32)
object INT64: Schema(type = Number, format = Int64)
object FLOAT: Schema(type = Number, format = themis.types.swagger.Float)
object DOUBLE: Schema(type = Number, format = themis.types.swagger.Double)
object STRING: Schema(type = themis.types.swagger.String)
object BYTE: Schema(type = themis.types.swagger.String, format = themis.types.swagger.Byte)
object BINARY: Schema(type = themis.types.swagger.String, format = Binary)
object DATE: Schema(type = themis.types.swagger.String, format = Date)
object DATETIME: Schema(type = themis.types.swagger.String, format = Datetime)
object PASSWORD: Schema(type = themis.types.swagger.String, format = Password)
object EMAIL: Schema(type = themis.types.swagger.String, format = Email)
object UUID: Schema(type = themis.types.swagger.String, format = Uuid)
object BOOLEAN: Schema(type = themis.types.swagger.Boolean)
object OBJECT: Schema(type = Object)
object ARRAY: Schema(type = Array)