package br.com.apinotesimplifier.interfaces;

import br.com.apinotesimplifier.models.PersonalData;
import br.com.apinotesimplifier.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndPersonalData {
  private User user;
  private PersonalData personalData;
}
