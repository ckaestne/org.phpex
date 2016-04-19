package org.phpex.statements

import org.phpex.environments.Environment

class EchoStatement(s:String) extends Statement {
  
  def execute(env:Environment): Environment = {
    return env.addOutput(s)
  }
  
  def symbolicExecute(env:Environment): Environment = {
    return env.addOutput(s)
  }
  
}