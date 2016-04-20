package org.phpex.statements

import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.bool.BooleanConstant
import org.phpex.values.concrete.StringValue
import org.scalatest.FlatSpec
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.values.concrete.IntegerValue
import org.phpex.values.symbolic.Choice

object IfStatementTest {
  
  def ifStatement1(b:Boolean): Statement = {
    IfStatement(BooleanConstant(b),
        EchoStatement("A"), 
        EchoStatement("B"))
  }
  
  def ifStatement2(b:Boolean): Statement = {
    IfStatement(BooleanConstant(b),
        AssignStatement("a", IntegerConstant(1)), 
        AssignStatement("a", IntegerConstant(0)))
  }

}

class IfStatementTest extends FlatSpec {
  
  "IfStatement simply executed" should "either print \"A\" or \"B\"" in {
    assert(IfStatementTest.ifStatement1(true).execute(SimpleEnvironment()).getOutput().top.equals(StringValue("A")))
    assert(IfStatementTest.ifStatement1(false).execute(SimpleEnvironment()).getOutput().top.equals(StringValue("B")))   
  }
   
  it should "either assign 1 or 0 to $a" in {
    assert(IfStatementTest.ifStatement2(true).execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(1)))
    assert(IfStatementTest.ifStatement2(false).execute(SimpleEnvironment()).lookup("a").equals(IntegerValue(0)))
  }
  
  "IfStatement symbolically executed" should "both print \"A\" or \"B\"" in {
    assert(IfStatementTest.ifStatement1(true).symbolicExecute(SimpleEnvironment()).getOutput().top.equals(Choice(BooleanConstant(true), StringValue("A"), StringValue("B"))) )
    assert(IfStatementTest.ifStatement1(false).symbolicExecute(SimpleEnvironment()).getOutput().top.equals(Choice(BooleanConstant(false), StringValue("A"), StringValue("B"))) )
    
  }

  it should "assign a CHOICE(b, 1, 0) to $a" in {
    assert(IfStatementTest.ifStatement2(true).symbolicExecute(SimpleEnvironment()).lookup("a").equals(Choice(BooleanConstant(true), IntegerValue(1), IntegerValue(0))))
    assert(IfStatementTest.ifStatement2(false).symbolicExecute(SimpleEnvironment()).lookup("a").equals(Choice(BooleanConstant(false), IntegerValue(1), IntegerValue(0))))
  }
}