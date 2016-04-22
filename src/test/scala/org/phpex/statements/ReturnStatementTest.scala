package org.phpex.statements

import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.Call
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.values.concrete.IntegerValue
import org.phpex.values.concrete.IntegerValue
import org.scalatest.FlatSpec
import org.phpex.values.SymbolicValue

object ReturnStatementTest extends App {

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
    
  /**
   * function euclid(x) {
   * 	...
   * }
   * a = euclid(35, 14) // => 7
   */
  def returnStatementExample3(): Statement = BlockStatement(
    FunctionDeclarationTest.euclid(),
    AssignStatement("a", Call("euclid", List(IntegerConstant(35), IntegerConstant(14)))))

}

class ReturnStatementTest extends FlatSpec {

  "Function 1 with return statement simply executed" should "return a correct value" in {
    assert(ReturnStatementTest.returnStatementExample1().execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(64)))
  }

  "Function 1 with return statement symbolically executed" should "return a correct value" in {
    assert(ReturnStatementTest.returnStatementExample1().symbolicExecute(SimpleEnvironment()).lookup("a").equals(IntegerValue(64)))
  }

  "Recursive 1 function with return statement simply executed" should "return a correct value" in {
    assert(ReturnStatementTest.returnStatementExample2().execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(0)))
  }

  "Recursive 1 function with return statement symbolically executed" should "detect recursion" in {
    assert(ReturnStatementTest.returnStatementExample2().symbolicExecute(SimpleEnvironment()).lookup("a").isInstanceOf[SymbolicValue])
  }
  //
  "Recursive 2 function (GCD, euclid) with return statement simply executed" should "return a correct value" in {
    assert(ReturnStatementTest.returnStatementExample3().execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(7)))
  }

  "Recursive 2 function (GCD, euclid) with return statement symbolically executed" should "detect recursion" in {
    assert(ReturnStatementTest.returnStatementExample3().symbolicExecute(SimpleEnvironment()).lookup("a").isInstanceOf[SymbolicValue])
  }
//
}