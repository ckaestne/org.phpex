package org.phpex.statements

import org.scalatest.FlatSpec
import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.Call
import org.phpex.values.concrete.IntegerValue
import org.phpex.values.concrete.IntegerValue
import org.phpex.expressions.integer.IntegerConstant

object ReturnStatementTest {
  
  /**
   * function f(x, y) {
   * 	return x * y;
   * }
   * a = f(2,3);
   */
  def returnStatementExample1(): Statement = BlockStatement(
      FunctionDeclarationTest.functionDeclaration1(), 
      AssignStatement("a", Call("f", List(IntegerConstant(2), IntegerConstant(32)))))
      
  /**
   * function f(x) {
   * 	if (x > 0) {
   * 		return f(x - 1);
   *   else {
   *   	return x;
   *   }
   * }
   * a = f(10)
   */
  def returnStatementExample2(): Statement = BlockStatement(
      FunctionDeclarationTest.recursiveExample1(), 
      AssignStatement("a", Call("f", List(IntegerConstant(10)))))

}

class ReturnStatementTest extends FlatSpec {
  
  "Function with return statement simply executed" should "return a correct value" in {
    assert(ReturnStatementTest.returnStatementExample1().execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(64)))
  }
  
  "Function with return statement symbolically executed" should "return a correct value" in {
    assert(false)
  }
  
  "Recursive function with return statement simply executed" should "return a correct value" in {
    assert(ReturnStatementTest.returnStatementExample2().execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(0)))
  }
  
  "Recursive function with return statement symbolically executed" should "detect recursion" in {
    assert(false)
  }
  
}