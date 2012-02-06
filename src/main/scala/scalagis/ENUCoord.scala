package scalagis

/** Coordinate in a local East-North-Up system.
 * 
 *  @param x local x (easting)
 *  @param y local y (northing)
 *  @param z local z (altitude)
 *  @param origin origin point (0,0,0) of the ENU system */
case class ENUCoord(x: Double, y: Double, z: Double, origin: EllipseCoord)
