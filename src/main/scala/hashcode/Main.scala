package hashcode

object Main
  extends App {

  import Arithm._

  var (metadata, rides) = WorkWithFile("a_example.in").toDomain.get

  println(metadata)

  var cars: List[Car] = (0 until metadata.F).map(Car(_)).toList

  var i = 0
  while (i < cars.size) {
    var newRide: Option[Ride] = None
    var car = cars(i)

    var j = 0
    while (car.timeSpent < metadata.T && (i == 0 || newRide.isDefined)) {
      newRide = rides.find { r =>
        (Math.max(r.start.x, car.coord.x) - Math.min(r.start.x, car.coord.x) <= j &&
          Math.max(r.start.y, car.coord.y) - Math.min(r.start.y, car.coord.y) <= j) &&
          !r.taken
      }

      newRide.foreach { nr =>
        val newCost = car.timeSpent + nr.s + delta(nr.start, car.coord) + delta(nr.start, nr.end)
        rides =
          nr.copy(taken = true) :: rides.filterNot(rr => newRide.contains(rr))

        car = car.copy(
          coord = nr.end,
          rides = nr :: car.rides,
          timeSpent = newCost)
      }

      println(s"newRide=$newRide")
      j += 1
    }
    cars = car :: cars.filterNot(_.id == car.id)

    i += 1
  }

  println(rides.mkString("\n"))
  println()
  println(cars.mkString("\n"))
}

object Arithm {
  def delta(c1: Coord, c2: Coord): Int =
    Math.max(c1.x, c2.x) - Math.min(c1.x, c2.x) + Math.max(c1.y, c2.y) - Math.min(c1.y, c2.y)

}