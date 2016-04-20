package org.phpex.statements

import org.phpex.environments.Environment

case class BlockStatement(statements:Statement*) extends Statement {
  
  def execute(env:Environment): Environment = {
    var environment = env
    statements.foreach { stmt => 
      environment = stmt.execute(environment) 
      
      // if a return statement has been executed, don't continue execution
      if (stmt.isInstanceOf[ReturnStatement]) {
        return environment
      }
      
    }
    return environment
  }
  
  def symbolicExecute(env:Environment): Environment = {
    var environment = env
    statements.foreach { stmt => 
      environment = stmt.symbolicExecute(environment) 
      
      // if a return statement has been executed, don't continue execution
      if (stmt.isInstanceOf[ReturnStatement]) {
        return environment
      }
      
    }
    return environment
  }
  
  override def toString() = statements mkString "\n"
}