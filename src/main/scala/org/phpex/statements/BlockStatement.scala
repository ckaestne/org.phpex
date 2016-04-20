package org.phpex.statements

import org.phpex.environments.Environment

case class BlockStatement(statements:List[Statement]) extends Statement {
  
  def execute(env:Environment): Environment = {
    var environment = env
    statements.foreach { stmt => 
      environment = stmt.execute(environment) 
      
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
      
      if (stmt.isInstanceOf[ReturnStatement]) {
        return environment
      }
    }
    return environment
  }
  
  override def toString() = statements mkString "\n"
}