package org.phpex.statements

import org.phpex.expressions.Expression
import org.phpex.values.Value
import org.scalatest.FlatSpec
import org.phpex.values.concrete.IntegerValue
import org.phpex.values.concrete.BooleanValue
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.bool.BooleanConstant
import org.phpex.environments.SimpleEnvironment
import org.phpex.values.concrete.StringValue
import org.phpex.expressions.StringConstant

object AssignStatementTest {
  def assignmentStatement1(e:Expression): Statement = {
    AssignStatement("a", e)
  }
}

class AssignStatementTest extends FlatSpec {
  
  "AssignStatement.simply executed" should "assign 0 to $a" in {
    assert(AssignStatementTest.assignmentStatement1(IntegerConstant(0)).execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(0)))
  }
  
  it should "assign TRUE to $a" in {
    assert(AssignStatementTest.assignmentStatement1(BooleanConstant(true)).execute(SimpleEnvironment()).lookup("a").equals(BooleanValue(true)))
  }
  
  it should "assign \"a\" to $a" in {
    assert(AssignStatementTest.assignmentStatement1(StringConstant("a")).execute(SimpleEnvironment()).lookup("a").equals(StringValue("a")))
  }
}