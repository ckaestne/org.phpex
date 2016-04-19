package org.phpex.values.concrete

import org.phpex.values.ConcreteValue

class IntegerValue(value:Int) extends ConcreteValue {
  def intValue = value
  def boolValue = if (value == 1) true else false
  override def toString = value.toString()
}