package hashcode

case class Metadata(
  R: Int,
  C: Int,
  F: Int,
  N: Int,
  B: Int,
  T: Int
)

case class Intersection(
  x: Int,
  y: Int
)

case class Ride(
  start: Intersection,
  end: Intersection,
  s: Int,
  f: Int
)
