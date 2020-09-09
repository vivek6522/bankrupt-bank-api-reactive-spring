package cc.vivp.bankrupt.model.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cc.vivp.bankrupt.model.AttributeConverters;
import cc.vivp.bankrupt.model.db.Transactions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
  Integer transactionId;
  String transactionReference;
  Integer source;
  Integer target;
  String amount;
  String description;

  @JsonIgnore
  public long getAmountInCents() {
    return AttributeConverters.convertToCents(amount);
  }

  public Transaction(Transactions transactionDbModel) {
    this.transactionId = transactionDbModel.getTransactionId();
    this.transactionReference = transactionDbModel.getTransactionReference();
    this.source = transactionDbModel.getSource();
    this.target = transactionDbModel.getTarget();
    this.amount = transactionDbModel.getAmount();
    this.description = transactionDbModel.getDescription();
  }
}
