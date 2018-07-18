package org.experiments.rsvoboda.config;

import org.eclipse.microprofile.config.spi.Converter;

public class CustomEmailConverter implements Converter<Email> {

  @Override
  public Email convert(String value) {
    return new Email(value);
  }

}
