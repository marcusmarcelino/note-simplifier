package br.com.apinotesimplifier.models;

import java.util.List;

import br.com.apinotesimplifier.dto.SellItemDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleFormObject {
  private Sale sale;
  private List<SellItemDTO> items;
}
