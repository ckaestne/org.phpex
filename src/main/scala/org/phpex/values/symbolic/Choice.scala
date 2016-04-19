package org.phpex.values.symbolic

import org.phpex.values.SymbolicValue
import org.phpex.values.Value
import org.phpex.expressions.Expression

class Choice(p:Expression, v1:Value, v2:Value) extends SymbolicValue {
  def intValue: Integer = 0
  def boolValue: Boolean = false
  //def stringValue: String
  
  override def toString() = "ite(" + p.toString + ", " + v1 + ", " + v2 + ")"
}
  
