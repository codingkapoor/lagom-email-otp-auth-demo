package com.codingkapoor.employee.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

case class Employee(id: Int, name: String, email: String, salary: Int)

object Employee {
  implicit val format: Format[Employee] = Json.format[Employee]
}

trait EmployeeService extends Service {

  def getEmployee(id: Int): ServiceCall[NotUsed, Employee]

  def isEmployeeRegistered(email: String): ServiceCall[NotUsed, Boolean]

  override def descriptor: Descriptor = {
    import Service._

    named("user")
      .withCalls(
        pathCall("/api/employees/:id", getEmployee _),
        pathCall("/api/employees?email", isEmployeeRegistered _)
      )
      .withAutoAcl(true)
  }
}
