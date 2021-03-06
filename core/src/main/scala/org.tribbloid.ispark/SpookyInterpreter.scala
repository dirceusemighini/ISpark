package org.tribbloid.ispark

/**
 * Created by peng on 22/07/14.
 */
class SpookyInterpreter(args: Seq[String], usejavacp: Boolean=true)
  extends SQLInterpreter(args, usejavacp) {

  override lazy val appName: String = "ISpooky"

  override def initializeSpark() {
    super.initializeSpark()

    interpret("""
import scala.concurrent.duration._
import org.tribbloid.spookystuff.entity.client._
import org.tribbloid.spookystuff.factory.driver._
import org.tribbloid.spookystuff.operator._
import org.tribbloid.spookystuff.SpookyContext
              """) match {
      case Results.Failure(ee) => throw new RuntimeException("SpookyContext failed to be imported", ee)
      case Results.Success(value) => return
      case _ => throw new RuntimeException("SpookyContext failed to be imported")
    }
  }
}
