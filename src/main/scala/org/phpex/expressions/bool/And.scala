package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.environments.Environment
import org.phpex.values.concrete.BooleanValue
import org.phpex.expressions.Expression
import org.phpex.expressions.Expression

case class And(b1:Expression, b2:Expression) extends BooleanExpression {
  def evaluate(env:Environment) = BooleanValue(b1.evaluate(env).boolValue && b2.evaluate(env).boolValue)
}