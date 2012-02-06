package scalagis

/** Coordinate expressed in an earth-centred, earth-fixed coordinate system.
 *  The x-axis is the line through the prime meridian at the equator.  The
 *  z-axis is the line through the north pole.  The y-axis is the vector
 *  result of z cross x (at 90-degrees longitude). */
case class ECEFCoord(x: Double, y: Double, z: Double)