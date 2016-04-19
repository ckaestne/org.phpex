package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.values.concrete.BooleanValue
import org.phpex.environments.Environment

case class BooleanConstant(value:Boolean) extends BooleanExpression {
  def evaluate(env:Environment) = new BooleanValue(value)
  
  override def toString = value.toString
}