package org.phpex.expressions

import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.bool.And
import org.phpex.expressions.bool.BooleanConstant
import org.phpex.expressions.bool.Equals
import org.phpex.expressions.bool.GreaterThan
import org.phpex.expressions.bool.LessThan
import org.phpex.expressions.bool.Not
import org.phpex.expressions.bool.Or
import org.phpex.expressions.integer.Add
import org.phpex.expressions.integer.IntegerConstant
import org.phpex.expressions.integer.Mod
import org.phpex.expressions.integer.Mul
import org.phpex.values.SymbolicValue
import org.phpex.values.concrete.BooleanValue
import org.phpex.values.concrete.IntegerValue
import org.phpex.values.symbolic.SymbolValue
import org.scalatest.FlatSpec

class ExpressionTest extends FlatSpec {
  
  "And(x, y).evaluate()" should "return correct results for non-symbolic values" in {
    assert(And(BooleanConstant(false), BooleanConstant(false)).evaluate(SimpleEnvironment()).equals(BooleanValue(false)))
    assert(And(BooleanConstant(false), BooleanConstant(true)).evaluate(SimpleEnvironment()).equals(BooleanValue(false)))
    assert(And(BooleanConstant(true), BooleanConstant(false)).evaluate(SimpleEnvironment()).equals(BooleanValue(false)))
    assert(And(BooleanConstant(true), BooleanConstant(true)).evaluate(SimpleEnvironment()).equals(BooleanValue(true)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(And(BooleanConstant(true), SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
  "Or(x, y).evaluate()" should "return correct results for non-symbolic values" in {
    assert(Or(BooleanConstant(false), BooleanConstant(false)).evaluate(SimpleEnvironment()).equals(BooleanValue(false)))
    assert(Or(BooleanConstant(false), BooleanConstant(true)).evaluate(SimpleEnvironment()).equals(BooleanValue(true)))
    assert(Or(BooleanConstant(true), BooleanConstant(false)).evaluate(SimpleEnvironment()).equals(BooleanValue(true)))
    assert(Or(BooleanConstant(true), BooleanConstant(true)).evaluate(SimpleEnvironment()).equals(BooleanValue(true)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(Or(BooleanConstant(true), SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
  "Equals(x, y).evaluate()" should "return correct results for non-symbolic values" in {
    assert(Equals(IntegerConstant(1), IntegerConstant(1)).evaluate(SimpleEnvironment()).equals(BooleanValue(true)))
    assert(Equals(IntegerConstant(1), IntegerConstant(0)).evaluate(SimpleEnvironment()).equals(BooleanValue(false)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(Equals(IntegerConstant(1), SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
  "GreaterThan(x, y).evaluate()" should "return correct results for non-symbolic values" in {
    assert(GreaterThan(IntegerConstant(1), IntegerConstant(0)).evaluate(SimpleEnvironment()).equals(BooleanValue(true)))
    assert(GreaterThan(IntegerConstant(0), IntegerConstant(1)).evaluate(SimpleEnvironment()).equals(BooleanValue(false)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(GreaterThan(IntegerConstant(0), SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
  "LessThan(x, y).evaluate()" should "return correct results for non-symbolic values" in {
    assert(LessThan(IntegerConstant(0), IntegerConstant(1)).evaluate(SimpleEnvironment()).equals(BooleanValue(true)))
    assert(LessThan(IntegerConstant(1), IntegerConstant(0)).evaluate(SimpleEnvironment()).equals(BooleanValue(false)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(LessThan(IntegerConstant(0), SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
  "Not(x).evaluate()" should "return correct results for non-symbolic values" in {
    assert(Not(BooleanConstant(true)).evaluate(SimpleEnvironment()).equals(BooleanValue(false)))
    assert(Not(BooleanConstant(false)).evaluate(SimpleEnvironment()).equals(BooleanValue(true)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(Not(SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
  "Add(x, y).evaluate()" should "return correct results for non-symbolic values" in {
    assert(Add(IntegerConstant(1), IntegerConstant(1)).evaluate(SimpleEnvironment()).equals(IntegerValue(2)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(Add(IntegerConstant(1), SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
  "Mod(x, y).evaluate()" should "return correct results for non-symbolic values" in {
    assert(Mod(IntegerConstant(5), IntegerConstant(2)).evaluate(SimpleEnvironment()).equals(IntegerValue(1)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(Mod(IntegerConstant(5), SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
  "Mul(x, y).evaluate()" should "return correct results for non-symbolic values" in {
    assert(Mul(IntegerConstant(2), IntegerConstant(3)).evaluate(SimpleEnvironment()).equals(IntegerValue(6)))
  }
  
  it should "return a symbolic value if at least one argument is symbolic" in {
    assert(Mul(IntegerConstant(2), SymbolValue("a")).evaluate(SimpleEnvironment()).isInstanceOf[SymbolicValue])
  }
  
}