package org.phpex.expressions

import org.phpex.values.Value
import org.phpex.values.concrete.StringValue
import org.phpex.environments.Environment

case class StringConstant(v:String) extends Expression {
  def evaluate(env:Environment): Value = StringValue(v)
  
  override def toString() = v
}