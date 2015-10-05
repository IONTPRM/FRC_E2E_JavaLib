package com.iontrading.frc_e2e.utils;

public class RobotLogger
{
  private static final String TRACE = "*TRACE*";
  private static final String DEBUG = "*DEBUG*";
  private static final String INFO = "*INFO* ";
  private static final String WARN = "*WARN* ";
  private static final String HTML = "*HTML* ";
  private String name;
  
  public static RobotLogger getLogger(String name)
  {
    return new RobotLogger(name);
  }
  
  private RobotLogger(String name)
  {
    this.name = name;
  }
  
  public void trace(String message)
  {
    message("*TRACE*", message);
  }
  
  public void debug(String message)
  {
    message("*DEBUG*", message);
  }
  
  public void info(String message)
  {
    message("*INFO* ", message);
  }
  
  public void warn(String message)
  {
    message("*WARN* ", message);
  }
  
  public void warn(String message, Throwable t)
  {
    message("*WARN* ", message);
    t.printStackTrace(System.out);
  }
  
  public void err(String message)
  {
    System.err.println(message);
  }
  
  public void err(Throwable t)
  {
    t.printStackTrace(System.err);
  }
  
  public void err(String message, Throwable t)
  {
    System.err.println(message);
    t.printStackTrace(System.err);
  }
  
  public void throwing(String tag, String message, Throwable t)
  {
    System.err.println(tag + ":" + message);
    t.printStackTrace(System.err);
  }
  
  public void html(String message)
  {
    message("*HTML* ", message + "<br/>");
  }
  
  private void message(String level, String message)
  {
    System.out.println(level + " " + message);
  }
}
