package org.phpex.expressions

import org.phpex.environments.Environment
import org.phpex.environments.SimpleEnvironment
import org.phpex.statements.AssignStatement
import org.phpex.statements.Statement
import org.phpex.values.Value
import org.phpex.values.concrete.FunctionValue
import org.phpex.values.symbolic.SymbolValue
import scala.collection.immutable.HashMap

case class Call(name: String, args: List[Expression]) extends Expression {

  def evaluate(env: Environment): Value = {

    Thread.currentThread().getStackTrace().foreach {
      s =>
        if (s.toString().contains("symbolicExecute")) {

          // recursion detection. If the function has already been called return a symbolic value
          if (env.getCalls().contains(name)) {
            return SymbolValue(name + "_" + env.hashCode())
          }
        }
    }

    if (env.lookup(name) == null) {
      return null
    }

    val function = env.lookup(name).asInstanceOf[FunctionValue]
    if (function.getArgs().size != args.size) {
      return null
    }

    /* First, create a new, empty environment with 
     * the arguments assigned to the function parameteters. */
    //val assignments = for ((name, expr) <- (function.getArgs() zip args)) yield new AssignStatement(name, expr).asInstanceOf[Statement]
    
    /* Push this.name to the call stack */
    var env2: Environment = SimpleEnvironment(env.getMap(), env.getOutput(), env.getCalls().push(this.name))
    for ((name, expr) <- (function.getArgs() zip args)) {
      env2 = env2.update(name, expr.evaluate(env))
    }
    /* Execute assignments */
    //assignments.foreach { a => env2 = a.execute(env2) }

    /* Execute the function body */
    env2 = function.body.execute(env2)

    /* Retrieve the return value (or null) and 
     * remove it from the program state */
    val returnValue = env2.lookup("return")
    if (returnValue != null) {
      env2 = SimpleEnvironment(env2.getMap() - "return", env2.getOutput, env2.getCalls)
    }

    return returnValue
  }

  override def toString() = name + "(" + (args mkString ",") + ")"

}