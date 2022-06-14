package br.com.apinotesimplifier.enums;

public enum EnumExampleWithDescription {
  UN("Unidade"),
  CX("Caixa");

  private String description;

  EnumExampleWithDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
