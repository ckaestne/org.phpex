package org.phpex.values.concrete

import org.phpex.values.Value
import org.phpex.statements.Statement
import org.phpex.statements.BlockStatement

case class FunctionValue(args:List[String], body:BlockStatement) extends Value {
  
  def getArgs() = args

  override def toString = "FUNCTION(" + (args mkString ",") + ") {\n" + body.toString() + "\n}"
}