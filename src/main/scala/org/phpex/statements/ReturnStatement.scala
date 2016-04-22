package org.phpex.statements

import org.phpex.expressions.Expression
import org.phpex.environments.Environment
import org.phpex.environments.SimpleEnvironment

case class ReturnStatement(e:Expression) extends Statement {
  
  def execute(env:Environment): Environment = {
    val env_ = env.update("return", e.evaluate(env))
    return new SimpleEnvironment(env_.getMap(), env_.getOutput(), env_.getCalls().pop)
  }
 
  def symbolicExecute(env:Environment): Environment = {
    val env_ = env.update("return", e.evaluate(env))
    return new SimpleEnvironment(env_.getMap(), env_.getOutput(), env_.getCalls().pop)
  }
  
  override def toString() = "return " + e.toString() + ";"
  
}