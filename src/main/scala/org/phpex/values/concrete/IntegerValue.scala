package org.phpex.values.concrete

import org.phpex.values.ConcreteValue

case class IntegerValue(value:Int) extends ConcreteValue {
  def intValue = value
  override def toString = value.toString()
}