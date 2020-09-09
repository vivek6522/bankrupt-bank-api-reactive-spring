package cc.vivp.bankrupt.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;

import cc.vivp.bankrupt.model.AttributeConverters;
import cc.vivp.bankrupt.model.api.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
  @Id
  Integer transactionId;
  String transactionReference;
  Integer source;
  Integer target;
  Long amountInCents;
  String description;

  @JsonIgnore
  public String getAmount() {
    return AttributeConverters.convertFromCents(amountInCents);
  }

  public Transactions(Transaction transactionApiModel) {
    this.transactionId = transactionApiModel.getTransactionId();
    this.transactionReference = transactionApiModel.getTransactionReference();
    this.source = transactionApiModel.getSource();
    this.target = transactionApiModel.getTarget();
    this.amountInCents = transactionApiModel.getAmountInCents();
    this.description = transactionApiModel.getDescription();
  }
}
