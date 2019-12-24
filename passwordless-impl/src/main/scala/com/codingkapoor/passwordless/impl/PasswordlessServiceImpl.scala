package com.codingkapoor.passwordless.impl

import akka.Done
import com.codingkapoor.passwordless.api.{PasswordlessService, Tokens}
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

class PasswordlessServiceImpl extends PasswordlessService {
  override def createOtp(): ServiceCall[Email, Done] = ServiceCall { email =>
    Future.successful(Done)
  }

  override def createTokens(): ServiceCall[OTP, Tokens] = ServiceCall { otp =>
    Future.successful(Tokens(otp.toString, otp.toString))
  }

  override def createJwt(): ServiceCall[Refresh, JWT] = ServiceCall { refresh =>
    Future.successful(refresh)
  }
}
