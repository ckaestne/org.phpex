package org.phpex.expressions.integer

import org.phpex.values.concrete.IntegerValue
import org.phpex.expressions.IntegerExpression
import org.phpex.environments.Environment
import org.phpex.expressions.Expression

case class Mod(i1:Expression, i2:Expression) extends IntegerExpression {
  def evaluate(env:Environment) = IntegerValue(i1.evaluate(env).asInstanceOf[IntegerValue].value % i2.evaluate(env).asInstanceOf[IntegerValue].value)
  override def toString() = "(" + i1 +" % " + i2 + ")"
}