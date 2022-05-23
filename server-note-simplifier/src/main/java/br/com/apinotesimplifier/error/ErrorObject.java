package br.com.apinotesimplifier.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorObject {
  private final String message;
  private final String field;
  private final String specificValidation;
  private final Object parameter;
}
