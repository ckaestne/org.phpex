package org.phpex.statements

import org.scalatest.FlatSpec
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.bool.LessThan
import org.phpex.expressions.integer.Add
import org.phpex.expressions.Variable
import org.phpex.expressions.Variable
import org.phpex.environments.SimpleEnvironment
import org.phpex.values.concrete.IntegerValue
import org.phpex.values.symbolic.Choice

object WhileStatementTest {
  
  /**
   * i = 0;
   * while (i < 10) {
   * 	i = i + 1;
   * }
   */
  def whileStatement1(): Statement = {
    BlockStatement(
      AssignStatement("i", IntegerConstant(0)),
      WhileStatement( LessThan(Variable("i"), IntegerConstant(10)),
        BlockStatement(AssignStatement("i", Add(Variable("i"), IntegerConstant(1))
      ))
    ))
  }

}

class WhileStatementTest extends FlatSpec {
  
  "WhileStatement simply executed" should "be executed as long as the condition holds" in {
    assert(WhileStatementTest.whileStatement1().execute(SimpleEnvironment()).lookup("i").equals(IntegerValue(10)))
  }
  
  "WhileStatement symbolically executed" should "be executed once (or not)" in {
    assert(WhileStatementTest.whileStatement1().symbolicExecute(SimpleEnvironment()).lookup("i").equals( Choice(LessThan(Variable("i"), IntegerConstant(10)), IntegerValue(1), IntegerValue(0)) ))
  }
}