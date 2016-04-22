package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.values.concrete.BooleanValue
import org.phpex.environments.Environment
import org.phpex.expressions.Expression
import org.phpex.values.SymbolicValue
import org.phpex.values.symbolic.SymbolValue
import org.phpex.values.Value

case class Or(b1: Expression, b2: Expression) extends BooleanExpression {

  def evaluate(env: Environment): Value = {
    val b1v = b1.evaluate(env)
    val b2v = b2.evaluate(env)
    if (b1v.isInstanceOf[BooleanValue] && b2v.isInstanceOf[BooleanValue]) {
      return BooleanValue(b1v.asInstanceOf[BooleanValue].value || b2v.asInstanceOf[BooleanValue].value)
    } else if (b1v.isInstanceOf[SymbolicValue] || b2v.isInstanceOf[SymbolicValue]) {
      return new SymbolValue(this.toString())
    } else {
      return null
    }
  }
  
  override def toString = "(" + b1 + " ∨ " + b2 + ")"
}