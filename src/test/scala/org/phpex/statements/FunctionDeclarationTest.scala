package org.phpex.statements

import org.phpex.statements.FunctionDeclaration
import org.phpex.statements.IfStatement
import org.scalatest.FlatSpec
import org.phpex.expressions.bool.GreaterThan
import org.phpex.expressions.integer.Add
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.integer.Mul
import org.phpex.values.concrete.FunctionValue
import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.Variable
import org.phpex.expressions.Call

object FunctionDeclarationTest {
  
  /**
   * function f(x, y) {
   * 	return x * y;
   * }
   */
  def functionDeclaration1(): Statement = FunctionDeclaration("f", List("x", "y"), BlockStatement(ReturnStatement(Mul(Variable("x"), Variable("y")))))
   
  /**
   * function f(x) {
   * 	if (x > 0) {
   * 		return f(x - 1);
   *   else {
   *   	return x;
   *   }
   * }
   */
  def recursiveExample1(): Statement = FunctionDeclaration("f", List("x"), 
      BlockStatement(
          IfStatement(GreaterThan(Variable("x"), IntegerConstant(0)),
              BlockStatement(ReturnStatement(Call("f", List(Add(Variable("x"), IntegerConstant(-1)))))),
              BlockStatement(ReturnStatement(Variable("x"))))) )
              
              

}

class FunctionDeclarationTest extends FlatSpec {
  
  "Function declaration" should "assign a function value to f" in {
    val f = FunctionValue(List("x", "y"), BlockStatement(ReturnStatement(Mul(Variable("x"), Variable("y")))))
    assert( FunctionDeclarationTest.functionDeclaration1().execute(SimpleEnvironment()).lookup("f").equals(f) )
  }
  
}