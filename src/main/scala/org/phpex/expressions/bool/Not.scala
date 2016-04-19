package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.values.concrete.BooleanValue
import org.phpex.environments.Environment

class Not(b:BooleanExpression) extends BooleanExpression {
  def evaluate(env:Environment) = new BooleanValue((! b.evaluate(env).boolValue))
}