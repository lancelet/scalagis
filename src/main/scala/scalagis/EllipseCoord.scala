package scalagis

import scala.math.cos
import scala.math.sin
import scala.math.sqrt

/** Coordinate expressed in a ellipsoid system. 
 *  
 *  A common ellipsoid system is the WGS-84 reference ellipsoid used in GPS
 *  coordinates.
 *  
 *  @param lat latitude (NB: in radians)
 *  @param lon longitude (NB: in radians)
 *  @param h height above reference ellipsoid 
 *  @param ellipsoid reference ellipsoid */
case class EllipseCoord(
    lat: Double, lon: Double, h: Double, ellipsoid: Ellipsoid = WGS84
) {
  
  /** Coordinate in an earth-centered, earth-fixed system. 
   *  
   *  Conversion equations given on:
   *    http://wiki.gis.com/wiki/index.php/Geodetic_system */
  lazy val ecef: ECEFCoord = {
    val a  = ellipsoid.semiMajorAxis
    val e2 = ellipsoid.eccentricitySq
    
    val cosLat = cos(lat)
    val sinLat = sin(lat)
    val chi = sqrt(1.0 - e2 * sinLat * sinLat)
    val c1 = a / chi + h
    val c2 = a * (1.0 - e2) / chi + h
    
    ECEFCoord(c1 * cosLat * cos(lon), c1 * cosLat * sin(lon), c2 * sin(lat))
  }
  
  /** Coordinate in a local East-North-Up system.
   * 
   *  Conversion equations given on:
   *    http://wiki.gis.com/wiki/index.php/Geodetic_system */
  def enu(origin: EllipseCoord): ENUCoord = {
    val sLat = sin(lat)
    val cLat = cos(lat)
    val sLon = sin(lon)
    val cLon = cos(lon)
    val dx = ecef.x - origin.ecef.x
    val dy = ecef.y - origin.ecef.y
    val dz = ecef.z - origin.ecef.z
    
    val x = -sLon * dx + cLon * dy
    val y = -sLat * cLon * dx - sLat * sLon * dy + cLat * dz
    val z = cLat * cLon * dx + cLat * sLon * dy + sLat * dz
    ENUCoord(x, y, z, origin)
  }
  
}