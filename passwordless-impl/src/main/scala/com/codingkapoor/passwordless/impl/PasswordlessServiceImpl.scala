package com.codingkapoor.passwordless.impl

import akka.Done
import com.codingkapoor.employee.api.EmployeeService
import com.codingkapoor.passwordless.api.{PasswordlessService, Tokens}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.transport.NotFound
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PasswordlessServiceImpl(employeeService: EmployeeService) extends PasswordlessService {
  override def createOtp(): ServiceCall[Email, Done] = ServiceCall { email =>
    employeeService.isEmployeeRegistered(email).invoke().map { res =>
      if (res) Done else throw NotFound(s"No employee found with email id = $email")
    }
  }

  override def createTokens(): ServiceCall[OTP, Tokens] = ServiceCall { otp =>
    // if the submitted otp is valid, create and reply jwt and refresh token
    Future.successful(Tokens(otp.toString, otp.toString))
  }

  override def createJwt(): ServiceCall[Refresh, JWT] = ServiceCall { refresh =>
    // if the submitted refresh token is valid, create and reply with a new jwt
    Future.successful(refresh)
  }
}
