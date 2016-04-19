package org.phpex.expressions.integer

import org.phpex.expressions.IntegerExpression
import org.phpex.values.concrete.IntegerValue
import org.phpex.environments.Environment

class IntegerConstant(value:Int) extends IntegerExpression {
  def evaluate(env:Environment) = new IntegerValue(value)
  
  override def toString() = value.toString()
}