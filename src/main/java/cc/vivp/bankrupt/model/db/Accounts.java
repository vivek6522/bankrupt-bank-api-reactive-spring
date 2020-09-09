package cc.vivp.bankrupt.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;

import cc.vivp.bankrupt.model.AttributeConverters;
import cc.vivp.bankrupt.model.api.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
  @Id
  Integer accountId;
  String accountNumber;
  String accountType;
  Long balanceInCents;
  Boolean preferred;
  String customerId;

  @JsonIgnore
  public String getBalance() {
    return AttributeConverters.convertFromCents(balanceInCents);
  }

  public Accounts(Account accountApiModel) {
    this.accountId = accountApiModel.getAccountId();
    this.accountNumber = accountApiModel.getAccountNumber();
    this.accountType = accountApiModel.getAccountType().toString();
    this.balanceInCents = accountApiModel.getBalanceInCents();
    this.preferred = accountApiModel.getPreferred();
    this.customerId = accountApiModel.getCustomerId();
  }
}
