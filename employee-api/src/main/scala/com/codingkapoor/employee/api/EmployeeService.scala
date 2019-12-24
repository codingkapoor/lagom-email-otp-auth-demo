package com.codingkapoor.employee.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

case class Employee(id: Int, name: String, salary: Int)

object Employee {
  implicit val format: Format[Employee] = Json.format[Employee]
}

trait EmployeeService extends Service {

  def getEmployee(id: Int): ServiceCall[NotUsed, Employee]

  override def descriptor: Descriptor = {
    import Service._

    named("user")
      .withCalls(
        pathCall("/api/employees/:id", getEmployee _)
      )
      .withAutoAcl(true)
  }
}
