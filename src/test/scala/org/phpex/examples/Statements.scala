package org.phpex.examples

import org.phpex.statements.Statement
import org.phpex.statements.AssignStatement
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.bool.BooleanConstant
import org.phpex.statements.IfStatement
import org.phpex.expressions.integer.Add
import org.phpex.expressions.Variable
import org.phpex.statements.WhileStatement
import org.phpex.expressions.bool.LessThan
import org.phpex.statements.BlockStatement
import org.phpex.environments.SimpleEnvironment
import org.phpex.values.Value
import org.phpex.environments.Environment
import org.phpex.statements.EchoStatement

object Statements {
  
  def assignStatement1(): Statement = new AssignStatement("a", new IntegerConstant(42))
  def assignStatement2(): Statement = new AssignStatement("a", new IntegerConstant(43))
  
  def ifStatement1(): Statement = new IfStatement(new BooleanConstant(true), assignStatement1(), assignStatement2())
  def ifStatement2(): Statement = new IfStatement(new BooleanConstant(false), assignStatement1(), assignStatement2())

  def iAssign(): Statement = new AssignStatement("i", new IntegerConstant(0))
  def increment(): Statement = new AssignStatement("i", new Add(new Variable("i"), new IntegerConstant(1)))

  def whileStatement1(): Statement = new BlockStatement(List(iAssign(), new WhileStatement(new LessThan(new Variable("i"), new IntegerConstant(10)), increment())))
  
  /**
   * echo "A";
   * echo "B";
   * echo "C";
   */
  def printStatement1(): Statement = new BlockStatement(List(new EchoStatement("A"), new EchoStatement("B"), new EchoStatement("C")))

  /**
   * echo "A";
   * if (b) {
   * 	echo "B";
   * } else { 
   * 	echo "C";
   * }
   */
  def printStatement2(): Statement = new BlockStatement(List(new EchoStatement("A"), new IfStatement(new Variable("b"), new EchoStatement("B"), new EchoStatement("C"))))
}