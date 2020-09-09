package cc.vivp.bankrupt.model.api;

import java.time.LocalDateTime;

import cc.vivp.bankrupt.model.db.TransactionStatuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatus {
  Integer transactionId;
  String status;
  LocalDateTime recorded;

  public TransactionStatus(TransactionStatuses transactionStatusDbModel) {
    this.transactionId = transactionStatusDbModel.getTransactionId();
    this.status = transactionStatusDbModel.getStatus();
    this.recorded = transactionStatusDbModel.getRecorded();
  }
}
