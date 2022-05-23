package br.com.apinotesimplifier.error;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
  private final String message;
  private final Integer code;
  private final String status;
  private final String objectName;
  private final List<ErrorObject> errors;
}
