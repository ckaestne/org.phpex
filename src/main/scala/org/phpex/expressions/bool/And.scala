package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.values.concrete.BooleanValue
import org.phpex.environments.Environment
import org.phpex.expressions.Expression

case class And(b1:Expression, b2:Expression) extends BooleanExpression {
  def evaluate(env:Environment): BooleanValue = BooleanValue(b1.evaluate(env).asInstanceOf[BooleanValue].value && b2.evaluate(env).asInstanceOf[BooleanValue].value)
}