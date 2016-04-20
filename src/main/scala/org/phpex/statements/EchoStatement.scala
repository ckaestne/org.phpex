package org.phpex.statements

import org.phpex.environments.Environment

case class EchoStatement(s:String) extends Statement {
  
  def execute(env:Environment) = env.addOutput(s)
  
  def symbolicExecute(env:Environment) = env.addOutput(s)
  
}