package org.jetbrains.plugins.template1;

public class Position {
  public int line;
  public int column;

  public Position(int line, int column) {
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }
}
