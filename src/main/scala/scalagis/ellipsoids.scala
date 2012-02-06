package scalagis

/** Reference ellipsoid (oblate spheroid). */
trait Ellipsoid {
  val semiMajorAxis: Double
  val reciprocalFlattening: Double
  
  lazy val flattening = 1.0 / reciprocalFlattening
  lazy val semiMinorAxis = semiMajorAxis * (1.0 - flattening)
  lazy val eccentricitySq = 2.0 * flattening - flattening * flattening
}

/** WGS84 Reference ellipsoid. */
case object WGS84 extends Ellipsoid {
  override val semiMajorAxis = 6378137.0
  override val reciprocalFlattening = 298.257223563
}
