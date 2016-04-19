package org.phpex.expressions.integer

import org.phpex.expressions.IntegerExpression
import org.phpex.expressions.Expression
import org.phpex.values.concrete.IntegerValue
import org.phpex.environments.Environment

class Add(i1:Expression, i2:Expression) extends IntegerExpression {
  def evaluate(env:Environment) = new IntegerValue(i1.evaluate(env).intValue + i2.evaluate(env).intValue)
  override def toString() = "Add("+i1+", "+i2+")"
}