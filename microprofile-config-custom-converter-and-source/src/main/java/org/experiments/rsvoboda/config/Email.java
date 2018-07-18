package org.experiments.rsvoboda.config;

public class Email {
  private String name;
  private String domain;

  public Email(String value) {
    String[] components = value.split("@");
    if (components.length == 2) {
      name = components[0];
      domain = components[1];
    }
  }

  public String getEmailName() {
    return name;
  }

  public String getEmailDomain() {
    return domain;
  }

  public String toString() {
    return name + "(at)" + domain;
  }
}
