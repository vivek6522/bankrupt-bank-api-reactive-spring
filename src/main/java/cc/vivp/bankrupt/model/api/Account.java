package cc.vivp.bankrupt.model.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cc.vivp.bankrupt.model.AccountType;
import cc.vivp.bankrupt.model.AttributeConverters;
import cc.vivp.bankrupt.model.db.Accounts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  Integer accountId;
  String accountNumber;
  AccountType accountType;
  String balance;
  Boolean preferred;
  String customerId;

  @JsonIgnore
  public long getBalanceInCents() {
    return AttributeConverters.convertToCents(balance);
  }

  public Account(Accounts accountDbModel) {
    this.accountId = accountDbModel.getAccountId();
    this.accountNumber = accountDbModel.getAccountNumber();
    this.accountType = AccountType.valueOf(accountDbModel.getAccountType());
    this.balance = accountDbModel.getBalance();
    this.preferred = accountDbModel.getPreferred();
    this.customerId = accountDbModel.getCustomerId();
  }
}
