package org.phpex.statements

import org.phpex.environments.Environment

class SkipStatement extends Statement {
  
  def execute(env:Environment) = env

  def symbolicExecute(env:Environment) = env
  
}