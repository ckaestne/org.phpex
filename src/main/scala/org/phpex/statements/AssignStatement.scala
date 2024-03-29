package org.phpex.statements

import org.phpex.expressions.Expression
import org.phpex.environments.Environment
import org.phpex.environments.SimpleEnvironment

case class AssignStatement(name:String, value:Expression) extends Statement {
  
  def execute(env:Environment) = env.update(name, value.evaluate(env))
  
  def symbolicExecute(env:Environment) = env.update(name, value.evaluate(env))

  override def toString() = name + " <- " + value.toString
  
}