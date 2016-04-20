package org.phpex.statements

import scala.collection.immutable.Stack

import org.phpex.environments.Environment
import org.phpex.environments.SimpleEnvironment
import org.phpex.expressions.Expression
import org.phpex.values.Value
import org.phpex.values.concrete.StringValue
import org.phpex.values.symbolic.Choice
import org.phpex.values.concrete.BooleanValue

case class IfStatement(pass: Expression, s1: BlockStatement, s2: BlockStatement) extends Statement {

  def execute(env: Environment): Environment = {
    if (pass.evaluate(env).asInstanceOf[BooleanValue].value) {
      return s1.execute(env)
    } else {
      return s2.execute(env)
    }
  }

  def symbolicExecute(env: Environment): Environment = {
    joinStates(env, s1.execute(env), s2.execute(env)) // pop since null is added prior to that
  }

  def joinStates(env: Environment, env1: Environment, env2: Environment): Environment = {
    var joinedMap = joinStateMaps(env1.getMap(), env2.getMap())
    var joinedOutput = joinStacks(pass, env1.getOutput(), env2.getOutput())
    return SimpleEnvironment(joinedMap, joinedOutput)
  }

  private def joinStacks(p: Expression, s1: Stack[Value], s2: Stack[Value]): Stack[Value] = {
    val prefix = pref(s1.reverse, s2.reverse)
    val output1 = s1.reverse.slice(prefix.size, s1.size)
    val output2 = s2.reverse.slice(prefix.size, s2.size)

    if (compareStacks(output1, output2)) {
      return prefix ++ output1
    } else {
      var stv1 = StringValue(output1.foldLeft("")((a, b) => a + b))
      var stv2 = StringValue(output2.foldLeft("")((a, b) => a + b))
      return prefix.push( Choice(p, stv1, stv2) ).reverse
    }
  }

  private def pref(s: Stack[Value], t: Stack[Value], out: Stack[Value] = Stack[Value]()): Stack[Value] = {
    if (s.isEmpty || t.isEmpty || !s(0).equals(t(0))) {
      return out
    } else {
      pref(s.slice(1, s.size), t.slice(1, t.size), out.push(s(0)))
    }
  }

  private def joinStateMaps(m1: Map[String, Value], m2: Map[String, Value]): Map[String, Value] = {

    var smap = Map[String, Value]()

    // get all keys that are in m1 but not in m2
    (m1.keySet -- m2.keySet).foreach {
      key => smap += key -> Choice(pass, m1.get(key).get, null)
    }

    // get all keys that are in m2 but not in m1
    (m2.keySet -- m1.keySet).foreach {
      key => smap += key -> Choice(pass, null, m2.get(key).get)
    }

    // get all keys that are in both m1 and m2
    (m1.keySet intersect m2.keySet).foreach { key =>
      if (!m1.get(key).get.toString().equals(m2.get(key).get.toString())) {
        smap += key -> Choice(pass, m1.get(key).get, m2.get(key).get)
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

    var a = s1
    var b = s2
    if (a.isEmpty != b.isEmpty) {
      return false
    }
    if (a.isEmpty && b.isEmpty) {
      return true
    }
    val element_a = a.top
    a = a.pop
    val element_b = b.top
    b = b.pop

    try {
      if (((element_a == null) && (element_b != null)) || (!element_a.equals(element_b)))
        return false
      return compareStacks(a, b)
    } finally { // restore elements
      a = a.push(element_a)
      b = b.push(element_b)
    }
  }

}