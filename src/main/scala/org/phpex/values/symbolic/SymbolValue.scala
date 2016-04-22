package org.phpex.values.symbolic

import org.phpex.values.SymbolicValue
import org.phpex.values.Value
import org.phpex.environments.Environment

case class SymbolValue(name:String) extends SymbolicValue {
  
  def evaluate(env:Environment): Value = this
  
}