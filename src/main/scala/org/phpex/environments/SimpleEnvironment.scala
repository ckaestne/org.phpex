package org.phpex.environments

import scala.collection.immutable.Map
import scala.collection.immutable.Stack

import org.phpex.values.Value
import org.phpex.values.concrete.StringValue
import scala.collection.immutable.HashMap
import org.phpex.expressions.Call

case class SimpleEnvironment(vars:Map[String, Value] = new HashMap[String, Value], output:Stack[Value] = new Stack[Value], calls:Stack[(Call, Environment)] = new Stack[(Call, Environment)]) extends Environment {
  
  def update(name:String, value:Value): Environment = {
    return SimpleEnvironment(vars + (name -> value), getOutput, getCalls)
  }
  
  def lookup(name:String) = vars.get(name).get
  
  def addOutput(s:String): Environment = {
    return SimpleEnvironment(getMap, output.push(new StringValue(s)), getCalls)
  }
  
  def getMap = vars
  def getOutput() = output
  def getCalls() = calls
  
  override def toString() = vars.toString() + " " + output.toString()
  
}