package org.phpex.statements

import scala.collection.immutable.Stack

import org.phpex.environments.Environment
import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.Expression
import org.phpex.expressions.Variable
import org.phpex.expressions.Variable
import org.phpex.values.Value
import org.phpex.values.concrete.StringValue
import org.phpex.values.symbolic.Choice

case class IfStatement(pass: Expression, s1: Statement, s2: Statement) extends Statement {

  def execute(env: Environment): Environment = {
    if (pass.evaluate(env).boolValue) {
      return s1.execute(env.addOutput(null))
    } else {
      return s2.execute(env.addOutput(null))
    }
  }

  /*
   * addOutput(null) for marking purpose
   */
  def symbolicExecute(env: Environment): Environment = {
    joinStates(env, s1.execute(env.addOutput(null)), s2.execute(env.addOutput(null)))// pop since null is added prior to that
  }

  private def joinStateMaps(m1: Map[String, Value], m2: Map[String, Value]): Map[String, Value] = {

    var smap = Map[String, Value]()

    // get all keys that are in m1 but not in m2
    (m1.keySet -- m2.keySet).foreach {
      key => smap += key -> new Choice(pass, m1.get(key).get, null)
    }

    // get all keys that are in m2 but not in m1
    (m2.keySet -- m1.keySet).foreach {
      key => smap += key -> new Choice(pass, null, m2.get(key).get)
    }

    // get all keys that are in both m1 and m2
    (m1.keySet intersect m2.keySet).foreach { key =>
      if (!m1.get(key).get.toString().equals(m2.get(key).get.toString())) {
        smap += key -> new Choice(pass, m1.get(key).get, m2.get(key).get)
      } else {
        smap += key -> m1.get(key).get // equals m2.get(key).get
      }
    }
    return smap
  }
  
  /**
   * Recursively compares two stacks of Value objects.
   */
  private def compareStacks(s1: Stack[Value], s2: Stack[Value]): Boolean = {
    if (s1.size != s2.size) {
      return false
    }
    if (s1.isEmpty) {
      true
    } else {

      // TODO evtly implement a equals operator for Values
      if (s1.top.toString.equals(s2.top.toString)) {
        return compareStacks(s1.pop, s2.pop)
      } else {
        return false
      }
    }
  }

  private def joinStacks(p:Expression, s:Stack[Value], s1: Stack[Value], s2: Stack[Value]): Stack[Value] = {

    var s1c = s1
    var output1 = new Stack[Value]
    while (s1c.top != null) {
      output1 = output1.push(s1c.top)
      s1c = s1c.pop
    }

    var s2c = s2
    var output2 = new Stack[Value]
    while (s2c.top != null) {
      output2 = output2.push(s2c.top)
      s2c = s2c.pop
    }

    if (compareStacks(output1, output2)) {
      return s.pop ++ output1 // pop since null is added prior to that
    } else {
      var stv1 = new StringValue(output1.foldLeft("")((a,b) => a + b))
      var stv2 = new StringValue(output2.foldLeft("")((a,b) => a + b))
      return s.pop.push( new Choice(p, stv1, stv2) ) // pop since null is added prior to that
    }

  }

  def joinStates(env:Environment, env1: Environment, env2: Environment): Environment = {
    var joinedMap = joinStateMaps(env1.getMap(), env2.getMap())
    var joinedOutput = joinStacks(pass, env.getOutput(), env1.getOutput(), env2.getOutput())
    return new SimpleEnvironment(joinedMap, joinedOutput)
  }

}