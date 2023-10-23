package com.example.mongodb_topic4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortField {
  ID("id"),
  USERNAME("name"),
  EMAIL("email"),
  STATUS("status");

  private final String databaseFieldName;
}
