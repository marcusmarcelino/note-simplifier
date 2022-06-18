package br.com.apinotesimplifier.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.apinotesimplifier.models.PaymentMethod;
import br.com.apinotesimplifier.models.SellItem;
import lombok.Data;

@Data
public class SaleFormDTO {
  private Long id;
  private List<SellItem> updatedItems = new ArrayList<>();
  private List<SellItem> addedItems = new ArrayList<>();
  private List<SellItem> removedItems = new ArrayList<>();
  private List<PaymentMethod> paymentMethods = new ArrayList<>();
  private Integer installmentForm;
}
