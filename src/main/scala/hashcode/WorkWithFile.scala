package hashcode

import java.net.URI

import scala.io.Source
import scala.util.Try

class WorkWithFile(fileName: String) {
  val file: URI = getClass.getClassLoader.getResource(fileName).toURI

  val toDomain: Option[(Metadata, List[Ride])] = Try {
    val (first :: rest) = Source.fromURI(file).getLines().toList
    val Array(r, c, f, n, b, t) = first.split(" ").map(_.toInt)
    val m = Metadata(r, c, f, n, b, t)
    val rides = rest.map { r =>
      val Array(a, b, x, y, s, f) = r.split(" ").map(_.toInt)
      Ride(
        start = Intersection(a, b),
        end = Intersection(x, y),
        s = s,
        f = f
      )
    }
    (m, rides)
  }.toOption
}

object WorkWithFile {
  def apply(fileName: String): WorkWithFile = new WorkWithFile(fileName)
}