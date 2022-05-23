package br.com.apinotesimplifier.error;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageExceptionHandler {
  private Date timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}
