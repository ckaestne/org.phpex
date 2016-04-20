package org.phpex.statements

import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.Call
import org.phpex.expressions.Variable
import org.phpex.expressions.bool.Equals
import org.phpex.expressions.bool.GreaterThan
import org.phpex.expressions.integer.Add
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.integer.Mod
import org.phpex.expressions.integer.Mul
import org.phpex.values.concrete.FunctionValue
import org.scalatest.FlatSpec

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
        BlockStatement(ReturnStatement(Variable("x"))))))
        
  /**
   * function euclid(a, b) {
   * 	if (b == 0) {
   * 		return a;	
   * 	} else {
   * 		return euclid(b, a % b);
   * 	}
   * }
   */
  def euclid(): Statement = {
    FunctionDeclaration("euclid", List("a", "b"),
      BlockStatement(
        IfStatement(Equals(Variable("b"), IntegerConstant(0)),
          BlockStatement(
            ReturnStatement(Variable("a"))),
          BlockStatement(
            ReturnStatement(Call("euclid", List(Variable("b"), Mod(Variable("a"), Variable("b")))))))))
  }

}

class FunctionDeclarationTest extends FlatSpec {

  "Function declaration" should "assign a function value to f" in {
    val f = FunctionValue(List("x", "y"), BlockStatement(ReturnStatement(Mul(Variable("x"), Variable("y")))))
    assert(FunctionDeclarationTest.functionDeclaration1().execute(SimpleEnvironment()).lookup("f").equals(f))
  }

}