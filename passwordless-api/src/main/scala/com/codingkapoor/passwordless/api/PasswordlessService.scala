package com.codingkapoor.passwordless.api

import akka.Done
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

case class Tokens(jwt: String, refresh: String)

object Tokens {
  implicit val format: Format[Tokens] = Json.format[Tokens]
}

trait PasswordlessService extends Service {

  type Email = String
  type OTP = Long
  type Refresh = String
  type JWT = String

  def createOtp(): ServiceCall[Email, Done]

  def createTokens(): ServiceCall[OTP, Tokens]

  def createJwt(): ServiceCall[Refresh, JWT]

  override def descriptor: Descriptor = {
    import Service._

    named("passwordless")
      .withCalls(
        restCall(Method.POST, "/api/passwordless/otp", createOtp _),
        restCall(Method.POST, "/api/passwordless/tokens", createTokens _),
        restCall(Method.POST, "/api/passwordless/jwt", createJwt _)
      )
  }
}
