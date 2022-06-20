package br.com.apinotesimplifier.enums;

public enum ProductType {
  UN("Unidade"),
  CX("Caixa");

  private String description;

  ProductType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
