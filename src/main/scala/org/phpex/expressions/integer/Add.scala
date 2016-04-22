package org.phpex.expressions.integer

import org.phpex.expressions.IntegerExpression
import org.phpex.expressions.Expression
import org.phpex.values.concrete.IntegerValue
import org.phpex.environments.Environment
import org.phpex.values.SymbolicValue
import org.phpex.values.symbolic.SymbolValue
import org.phpex.values.Value

case class Add(i1: Expression, i2: Expression) extends IntegerExpression {
  
  def evaluate(env: Environment): Value = {
    val i1v = i1.evaluate(env)
    val i2v = i2.evaluate(env)
    if (i1v.isInstanceOf[IntegerValue] && i2v.isInstanceOf[IntegerValue]) {
      return IntegerValue(i1v.asInstanceOf[IntegerValue].intValue + i2v.asInstanceOf[IntegerValue].intValue)
    } else if (i1v.isInstanceOf[SymbolicValue] || i2v.isInstanceOf[SymbolicValue]) {
      return SymbolValue(this.toString())
    } else {
      return null
    }
  }
  
  override def toString(): String = {
    "(" + i1 + " + " + i2 + ")"
  }
}