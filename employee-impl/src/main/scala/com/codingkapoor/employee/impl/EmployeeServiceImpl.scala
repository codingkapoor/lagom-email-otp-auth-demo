package com.codingkapoor.employee.impl

import akka.NotUsed
import com.codingkapoor.employee.api.{Employee, EmployeeService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.transport.NotFound

import scala.concurrent.Future

class EmployeeServiceImpl extends EmployeeService {

  private val employees =
    Seq(
      Employee(1, "Shivam Kapoor", "mail@shivamkapoor.com", 1000),
      Employee(2, "Kunal Mehra", "kunal.mehra@gmail.com", 1100)
    )

  override def getEmployee(id: Int): ServiceCall[NotUsed, Employee] = ServiceCall { _ =>
    val found = employees.filter(e => e.id == id)
    if (found.nonEmpty)
      Future.successful(found.head)
    else
      throw NotFound(s"No employee with id = $id found.")
  }

  override def isEmployeeRegistered(email: String): ServiceCall[NotUsed, Boolean] = ServiceCall { _ =>
    val found = employees.filter(e => e.email == email)
    Future.successful(found.nonEmpty)
  }
}
