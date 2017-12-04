@file:Suppress("NOTHING_TO_INLINE")

package org.frice.obj

import org.frice.utils.shape.FPoint
import org.frice.utils.shape.FShapeQuad

/**
 * Created by ice1000 on 2016/8/18.
 * @author ice1000
 * @since v0.3.3
 */
interface AbstractObject {
	var x: Double
	var y: Double

	var rotate: Double
}

/**
 * Created by ice1000 on 2016/8/20.
 * @author ice1000
 * @since v0.4
 */
interface FContainer {

	var x: Double
	var y: Double

	val width: Double
	val height: Double

	fun containsPoint(px: Int, py: Int) = px >= x && px <= x + width && py >= y && py <= y + height

	operator fun contains(point: FPoint) = containsPoint(point.x, point.y)
}

/**
 * Created by ice1000 on 2016/8/16.
 * @author ice1000
 * @since v0.3
 */
interface Collidable {
	/**
	 * @since v1.6.7
	 * @see org.frice.utils.shape.FQuad
	 */
	val box: FShapeQuad

	fun collides(other: Collidable): Boolean = box.run {
		val rect = other.box
		x + width >= rect.x && rect.y <= y + height &&
			x <= rect.x + rect.width &&
			y <= rect.y + rect.height
	}
}

/**
 * Created by ice1000 on 2016/8/19.
 *
 * @author ice1000
 * @since v0.4
 */
abstract class PhysicalObject : AbstractObject, Collidable, FContainer {
	open var died = false
	override var rotate = 0.0
	var mass = 1.0
		set(value) {
			field = if (value <= 0) 0.001 else value
		}
}
