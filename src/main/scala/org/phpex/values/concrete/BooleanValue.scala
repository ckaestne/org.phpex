package org.phpex.values.concrete

import org.phpex.values.ConcreteValue

class BooleanValue(value:Boolean) extends ConcreteValue {
  def intValue = if (value) 1 else 0
  def boolValue = value
  override def toString = value.toString
}