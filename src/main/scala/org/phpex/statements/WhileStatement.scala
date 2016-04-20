package org.phpex.statements

import org.phpex.expressions.Expression
import org.phpex.environments.Environment

case class WhileStatement(pass:Expression, s1:Statement) extends Statement {
  
  def execute(env:Environment): Environment = {
    var environment = env
    while (pass.evaluate(environment).boolValue) {
      environment = s1.execute(environment)
    }
    return environment
  }
  
  /**
   * For now, a loop will only be symbolically executed once.
   */
  def symbolicExecute(env:Environment): Environment = {
    val transformed = new IfStatement(pass, s1, new SkipStatement())
    return transformed.symbolicExecute(env)
  }
  
}