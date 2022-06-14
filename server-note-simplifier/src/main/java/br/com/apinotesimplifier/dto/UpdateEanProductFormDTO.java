package br.com.apinotesimplifier.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEanProductFormDTO {
  private Long id;
  private String ean;
  private String eanMain;
}
