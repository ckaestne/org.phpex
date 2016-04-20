package org.phpex.statements

import org.phpex.environments.Environment
import org.phpex.values.concrete.FunctionValue
import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach

case class FunctionDeclaration(name:String, args:List[String], body:BlockStatement) extends Statement {
  
  def execute(env:Environment): Environment = {
    return env.update(name, FunctionValue(args, body))
  }
  
  def symbolicExecute(env:Environment): Environment = {
    return env.update(name, FunctionValue(args, body))
  }
  
  override def toString() = "define " + name + "(" + args.foreach { arg => arg } + ") {" + body + "}"
  
}