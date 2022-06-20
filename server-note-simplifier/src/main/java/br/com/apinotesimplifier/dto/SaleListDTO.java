package br.com.apinotesimplifier.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.apinotesimplifier.models.Sale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleListDTO {
  private Long id;
  private Long idClient;
  private Long idSeller;
  private Long idSalePayment;
  private LocalDate dateOfSale;
  private BigDecimal vlTotal;

  public SaleListDTO(Sale sale) {
    this.id = sale.getId();
    this.idClient = sale.getIdClient().getId();
    this.idSeller = sale.getIdSeller().getId();
    this.idSalePayment = sale.getIdSalePayment().getId();
    this.dateOfSale = sale.getDateOfSale();
    this.vlTotal = sale.getVlTotal();
  }
}
