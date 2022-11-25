package com.osmos.intercom.exception;

public class ResourceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ResourceException(String msg, Throwable e) {
    super(msg, e);
  }
}
