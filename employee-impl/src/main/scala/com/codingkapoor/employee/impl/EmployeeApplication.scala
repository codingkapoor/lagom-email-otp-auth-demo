package com.codingkapoor.employee.impl

import com.codingkapoor.employee.api.EmployeeService
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext}
import play.api.libs.ws.ahc.AhcWSComponents
import com.softwaremill.macwire._

abstract class EmployeeApplication(context: LagomApplicationContext) extends LagomApplication(context) with AhcWSComponents {
  override lazy val lagomServer = serverFor[EmployeeService](wire[EmployeeServiceImpl])
}
