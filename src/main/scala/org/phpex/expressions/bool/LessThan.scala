package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.expressions.Expression
import org.phpex.values.concrete.BooleanValue
import org.phpex.environments.Environment

case class LessThan(i1:Expression, i2:Expression) extends BooleanExpression {
  def evaluate(env:Environment) = BooleanValue(i1.evaluate(env).intValue < i2.evaluate(env).intValue)

  override def toString() = i1 + " < " + i2
}