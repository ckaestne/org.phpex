package org.phpex.statements

import org.phpex.environments.Environment

class BlockStatement(statements:List[Statement]) extends Statement {
  
  def execute(env:Environment): Environment = {
    println("fehler")
    var environment = env
    statements.foreach { stmt => environment = stmt.execute(environment) }
    return environment
  }
  
  def symbolicExecute(env:Environment): Environment = {
    println("auch hier")
    var environment = env
    statements.foreach { stmt => environment = stmt.symbolicExecute(environment) }
    return environment
  }
}