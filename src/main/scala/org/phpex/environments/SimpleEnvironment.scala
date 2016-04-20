package org.phpex.environments

import scala.collection.immutable.Map
import scala.collection.immutable.Stack

import org.phpex.values.Value
import org.phpex.values.concrete.StringValue
import scala.collection.immutable.HashMap

case class SimpleEnvironment(vars:Map[String, Value] = new HashMap[String, Value], output:Stack[Value] = new Stack[Value]) extends Environment {
  
  def update(name:String, value:Value): Environment = {
    return SimpleEnvironment(vars + (name -> value), getOutput)
  }
  
  def lookup(name:String) = vars.get(name).get
  
  def addOutput(s:String): Environment = {
    return SimpleEnvironment(getMap, output.push(new StringValue(s)))
  }
  
  def getMap = vars
  def getOutput() = output
  
  override def toString() = vars.toString() + " " + output.toString()
  
}