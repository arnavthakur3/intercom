package com.osmos.intercom.enums;

public enum SchemaObjectEnum {
  CONVERSATIONS("conversations"), TAGS("tags"), CONTACTS(
      "contacts");

  private String streamObjectName;

  SchemaObjectEnum(String streamObjName) {
    this.streamObjectName = streamObjName;
  }

  public String getStreamObjectName() {
    return streamObjectName;
  }
}
