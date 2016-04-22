package org.phpex.values.symbolic

import org.phpex.values.SymbolicValue
import org.phpex.values.Value
import org.phpex.expressions.Expression
import org.phpex.environments.Environment
import org.phpex.values.concrete.BooleanValue

case class Choice(p:Expression, v1:Value, v2:Value) extends SymbolicValue {
  def intValue: Integer = 0
  def boolValue: Boolean = false
  
  def evaluate(env:Environment): Value = {
    val pv = p.evaluate(env)
    if (pv.isInstanceOf[BooleanValue]) {
      if (pv.asInstanceOf[BooleanValue].equals(true)) {
        return v1
      } else {
        return v2
      }
    } else {
      return null
    }
  }
  
  override def toString() = "CHOICE(" + p.toString + ", " + v1 + ", " + v2 + ")"
}
  
