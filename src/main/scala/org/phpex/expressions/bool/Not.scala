package org.phpex.expressions.bool

import org.phpex.expressions.BooleanExpression
import org.phpex.values.concrete.BooleanValue
import org.phpex.environments.Environment
import org.phpex.expressions.Expression
import org.phpex.values.symbolic.SymbolValue
import org.phpex.values.Value

case class Not(b:Expression) extends BooleanExpression {
  
  def evaluate(env:Environment): Value = {
    val bv = b.evaluate(env)
    if (bv.isInstanceOf[BooleanValue]) {
      return BooleanValue((! b.evaluate(env).asInstanceOf[BooleanValue].value))
    } else {
      return SymbolValue( this.toString() )
    }
    
  }
}