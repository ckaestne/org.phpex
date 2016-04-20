package org.phpex.expressions.bool

import org.phpex.expressions.IntegerExpression
import org.phpex.expressions.Expression
import org.phpex.values.concrete.IntegerValue
import org.phpex.environments.Environment
import org.phpex.values.concrete.BooleanValue

case class Equals(i1:Expression, i2:Expression) extends IntegerExpression {
  def evaluate(env:Environment) = BooleanValue(i1.evaluate(env).asInstanceOf[IntegerValue].value.intValue() == i2.evaluate(env).asInstanceOf[IntegerValue].value.intValue())
  override def toString(): String = {
    "(" + i1 +" == " + i2 + ")"
  }
}