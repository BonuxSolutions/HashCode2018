package hashcode

case class Metadata(
  R: Int,
  C: Int,
  F: Int,
  N: Int,
  B: Int,
  T: Int
)

case class Coord(
  x: Int,
  y: Int
)

case class Car(
  id: Int,
  rides: List[Ride] = Nil,
  coord: Coord = Coord(0, 0),
  timeSpent: Int = 0
)

case class Ride(
  start: Coord,
  end: Coord,
  s: Int,
  f: Int,
  taken: Boolean = false
)
