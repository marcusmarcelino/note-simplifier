package br.com.apinotesimplifier.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.apinotesimplifier.models.Sale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleDTO {
  private Long id;
  private Long idClient;
  private Long idSeller;
  private List<SellItemDTO> sellItems = new ArrayList<>();
  private Long idSalePayment;
  private LocalDate dateOfSale;

  public SaleDTO() {
  }

  public SaleDTO(
      Long id,
      Long idClient,
      Long idSeller,
      List<SellItemDTO> sellItems,
      Long idSalePayment,
      LocalDate dateOfSale) {
    this.id = id;
    this.idClient = idClient;
    this.idSeller = idSeller;
    this.sellItems = sellItems;
    this.idSalePayment = idSalePayment;
    this.dateOfSale = dateOfSale;
  }

  public SaleDTO(Sale sale) {
    this.id = sale.getId();
    this.idClient = sale.getIdClient().getId();
    this.idSeller = sale.getIdSeller().getId();
    this.sellItems = sale.getSellItems()
        .stream()
        .map(sellItem -> new SellItemDTO(sellItem))
        .collect(Collectors.toList());
    this.idSalePayment = sale.getIdSalePayment().getId();
    this.dateOfSale = sale.getDateOfSale();
  }
}
