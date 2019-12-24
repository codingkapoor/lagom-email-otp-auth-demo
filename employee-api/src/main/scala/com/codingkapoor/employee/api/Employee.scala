package com.codingkapoor.employee.api

import play.api.libs.json.{Format, Json}

case class Employee(id: Int, name: String, salary: Int)

object Employee {
  implicit val format: Format[Employee] = Json.format[Employee]
}
