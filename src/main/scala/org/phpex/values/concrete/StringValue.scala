package org.phpex.values.concrete

import org.phpex.values.ConcreteValue

case class StringValue(value:String) extends ConcreteValue {
  override def toString = value
}