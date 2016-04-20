package org.phpex.statements

import org.scalatest.Suites

class StatementTestSuite extends Suites (
    new AssignStatementTest,
    new BlockStatementTest,
    new IfStatementTest,
    new WhileStatementTest,
    new ReturnStatementTest,
    new FunctionDeclarationTest
)