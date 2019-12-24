package com.codingkapoor.employee.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

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
