package org.phpex.statements

import org.phpex.environments.Environment

trait Statement {
  def execute(env:Environment): Environment
  def symbolicExecute(env:Environment): Environment
}