package org.phpex.statements

import org.phpex.expressions.Expression
import org.phpex.environments.Environment
import org.phpex.statements.SkipStatement
import org.phpex.values.concrete.BooleanValue

case class WhileStatement(pass:Expression, s1:BlockStatement) extends Statement {
  
  def execute(env:Environment): Environment = {
    var environment = env
    while (pass.evaluate(environment).asInstanceOf[BooleanValue].value) {
      environment = s1.execute(environment)
    }
    return environment
  }
  
  /**
   * For now, a loop will only be symbolically executed once.
   */
  def symbolicExecute(env:Environment): Environment = {
    val transformed = IfStatement(pass, s1, BlockStatement(new SkipStatement()))
    return transformed.symbolicExecute(env)
  }
  
}