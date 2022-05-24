package br.com.apinotesimplifier.models;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
  private String district;
  private String road;
  private String number;
  private String complement;
  private String city;
  private String uf;
}