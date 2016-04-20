package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.values.concrete.BooleanValue
import org.phpex.environments.Environment

case class Not(b:BooleanExpression) extends BooleanExpression {
  def evaluate(env:Environment) = BooleanValue((! b.evaluate(env).asInstanceOf[BooleanValue].value))
}