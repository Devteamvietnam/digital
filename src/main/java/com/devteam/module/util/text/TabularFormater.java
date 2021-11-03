package com.devteam.module.util.text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TabularFormater implements TextFormater {
  static DecimalFormat DOUBLE_FORMATER = new DecimalFormat("#0.##");

  private String         indent ;
  private String         title = "";
  private List<String>   footers = new ArrayList<>();
  private List<String>   colHeaders;
  private List<String[]> rows;
  private boolean        allowSeparateLine = true;


  public TabularFormater() {
    this.colHeaders = new ArrayList<String>();
    this.rows = new ArrayList<String[]>() ;
  }

  public TabularFormater(String ... colHeader) {
    this.colHeaders = new ArrayList<String>(Arrays.asList(colHeader));
    this.rows = new ArrayList<String[]>() ;
  }

  public void setTitle(String title) {
    this.title = title ;
  }

  public TabularFormater withAllowSeparateLine(boolean allowSeparateLine) {
    this.allowSeparateLine = allowSeparateLine;
    return this;
  }

  public void addFooter(String mesg) {
    footers.add(mesg) ;
  }

  public void setIndent(String indent) { this.indent = indent ; }

  public void header(String ... colHeader) {
    this.colHeaders = new ArrayList<String>(Arrays.asList(colHeader));
    this.rows = new ArrayList<String[]>() ;
  }

  public void header(List<String> headers) {
    this.colHeaders = headers;
    this.rows = new ArrayList<String[]>() ;
  }

  public void addRow(List<Object> cellList) {
    Object[] cells = cellList.toArray(new Object[cellList.size()]);
    addRow(cells);
  }

  public void addRow(Object ... cellData) {
    if(cellData.length != colHeaders.size()) {
      throw new RuntimeException("Expect " + colHeaders.size() + " cells insteader of " + cellData.length) ;
    }
    String[] cell = new String[cellData.length] ;
    for(int i = 0; i < cell.length; i++) {
      Object selCell = cellData[i];
      if(selCell instanceof Double) selCell = DOUBLE_FORMATER.format((Double)selCell);
      if(cellData[i] == null) cell[i] = "" ;
      else cell[i] = selCell.toString() ;
      int len = cell[i].length();
      if (len > 80) {
        cell[i] = cell[i].substring(0, 80) + "...";
      }
    }
    rows.add(cell) ;
  }

  public void addRows(Object[][] cells) {
    for(Object[] row : cells) addRow(row);
  }

  public String getFormattedText() {
    StringBuilder b = new StringBuilder() ;
    print(b) ;
    return b.toString() ;
  }

  public void print(Appendable out) {
    int[] width = new int[colHeaders.size()] ;
    for(int i = 0; i < width.length; i++) {
      width[i] = colHeaders.get(i).length() ;
    }

    for(int i = 0; i < rows.size(); i++) {
      String[] cell = rows.get(i) ;
      for(int j = 0; j < cell.length; j++) {
        if(cell[j].length() > width[j]) {
          width[j] = cell[j].length() ;
        }
      }
    }

    int lineLength = 0 ;
    for(int i = 0; i < colHeaders.size(); i++) {
      lineLength += width[i] + 3 ;
    }
    lineLength = lineLength > title.length() ? lineLength : title.length();
    print(out, "\n") ;
    if(StringUtil.isNotBlank(title)) {
      print(out, indent) ;
      print(out, title) ;
      print(out, "\n") ;
      printLine(out, lineLength);
      print(out, "\n") ;
    }

    print(out, indent) ;
    for(int i = 0; i < colHeaders.size(); i++) {
      printCell(out, colHeaders.get(i), width[i]) ;
    }
    print(out, "\n") ;
    printLine(out, lineLength);
    print(out, "\n") ;

    for(int i = 0; i < rows.size(); i++) {
      print(out, indent) ;
      String[] cell = rows.get(i) ;
      for(int j = 0; j < cell.length; j++) {
        printCell(out,  cell[j], width[j]) ;
      }
      print(out, "\n") ;
    }
    printLine(out, lineLength);
    if(footers.size() > 0 ) {
      for(String mesg : footers) {
        print(out, indent) ;
        print(out, "*" + mesg) ;
        print(out, "\n") ;
      }
      printLine(out, lineLength);
    }
  }


  private void printLine(Appendable out, int lineLength) {
    if(!allowSeparateLine) return;
    print(out, indent) ;
    for(int i = 0; i < lineLength; i++) {
      print(out, "_") ;
    }
  }

  private void printCell(Appendable out, String cell, int width) {
    int len = cell.length();
    print(out, cell);
    for (int i = len; i < width; i++) {
      print(out, " ");
    }
    print(out, "  ");
  }

  private void print(Appendable out, String string) {
    if(string == null) return ;
    try {
      out.append(string);
    } catch(Exception ex) {
      throw new RuntimeException("Append error", ex) ;
    }
  }

  static public void main(String[] args) {
    String[] header = { "header 1", "header 2", "header 3" };
    TabularFormater formater = new TabularFormater(header);
    formater.setTitle("TabularFormater");
    for (int i = 0; i < 10; i++) {
      formater.addRow("column 1", "this is the column 2", "my column 3");
    }
    System.out.println(formater.getFormattedText());
  }
}