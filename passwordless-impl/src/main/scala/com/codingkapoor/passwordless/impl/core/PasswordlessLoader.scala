package com.codingkapoor.passwordless.impl.core

import com.codingkapoor.employee.api.EmployeeService
import com.codingkapoor.passwordless.api.PasswordlessService
import com.codingkapoor.passwordless.impl.service.{MailOTPService, PasswordlessServiceImpl}
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader, LagomServer}
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents

abstract class PasswordlessApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {
  override lazy val lagomServer: LagomServer = serverFor[PasswordlessService](wire[PasswordlessServiceImpl])

  lazy val mailOTPService: MailOTPService = wire[MailOTPService]
  lazy val employeeService: EmployeeService = serviceClient.implement[EmployeeService]
}

class PasswordlessLoader extends LagomApplicationLoader {
  override def load(context: LagomApplicationContext): LagomApplication =
    new PasswordlessApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new PasswordlessApplication(context) with LagomDevModeComponents

  override def describeService: Option[Descriptor] = Some(readDescriptor[PasswordlessService])
}
