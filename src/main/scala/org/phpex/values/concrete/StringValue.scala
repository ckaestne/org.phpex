package org.phpex.values.concrete

import org.phpex.values.ConcreteValue

class StringValue(value:String) extends ConcreteValue {
  def intValue = 0
  def boolValue = false
  override def toString = value
}