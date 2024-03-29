package org.phpex.expressions

import org.phpex.environments.Environment
import org.phpex.values.Value

case class Variable(name:String) extends Expression {
  
  def evaluate(env:Environment) = env.lookup(name)
  
  override def toString() = name
}