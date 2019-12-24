package com.codingkapoor.employee.impl

import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader, LagomServer}
import com.codingkapoor.employee.api.EmployeeService
import play.api.libs.ws.ahc.AhcWSComponents
import com.softwaremill.macwire._

abstract class EmployeeApplication(context: LagomApplicationContext) extends LagomApplication(context) with AhcWSComponents {
  override lazy val lagomServer: LagomServer = serverFor[EmployeeService](wire[EmployeeServiceImpl])
}

class EmployeeLoader extends LagomApplicationLoader {
  override def load(context: LagomApplicationContext): LagomApplication =
    new EmployeeApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new EmployeeApplication(context) with LagomDevModeComponents

  override def describeService: Option[Descriptor] = Some(readDescriptor[EmployeeService])
}
