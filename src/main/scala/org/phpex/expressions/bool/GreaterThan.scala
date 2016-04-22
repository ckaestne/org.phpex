package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.expressions.Expression
import org.phpex.values.concrete.BooleanValue
import org.phpex.environments.Environment
import org.phpex.values.concrete.IntegerValue
import org.phpex.values.SymbolicValue
import org.phpex.values.symbolic.SymbolValue
import org.phpex.values.Value

case class GreaterThan(i1:Expression, i2:Expression) extends BooleanExpression {
  
  def evaluate(env:Environment): Value = {
    val i1v = i1.evaluate(env)
    val i2v = i2.evaluate(env)
    if (i1v.isInstanceOf[IntegerValue] && i2v.isInstanceOf[IntegerValue]) {
      return BooleanValue(i1v.asInstanceOf[IntegerValue].intValue > i2v.asInstanceOf[IntegerValue].intValue)
    } else if (i1v.isInstanceOf[SymbolicValue] || i2v.isInstanceOf[SymbolicValue]) {
      return new SymbolValue( this.toString() )
    } else {
      return null
    }
  }
  override def toString() = i1 + " > " + i2
}