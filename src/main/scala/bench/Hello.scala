package bench

import cats.implicits._
import org.openjdk.jmh.annotations._
import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.types.numeric._
import eu.timepit.refined.numeric._
import scala.util._

class JMHSample {
  @throws
  def convertPositiveInt(i: Int): Int = {
    require(i >= 0)
    i
  }

  @Benchmark
  def measureAgreementPosInt(): Either[String, Int] = {
    try {
      Right[String, Int](convertPositiveInt(1))
    } catch {
      case t: Throwable => Left[String, Int](t.getMessage)
    }
  }

  @Benchmark
  def measureAgreementPosIntFPLike(): Either[String, Int] = {
    Try(convertPositiveInt(1)).toEither.leftMap(t => t.getMessage)
  }

  @Benchmark
  def measurePosInt(): Either[String, PosInt] = {
    RefType.applyRef[PosInt](1)
  }

  @Benchmark
  def measureIntRefinedPositive(): Either[String, Int Refined Positive] = {
    refineV[Positive](1)
  }
}
