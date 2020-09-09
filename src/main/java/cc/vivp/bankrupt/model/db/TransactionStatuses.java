package cc.vivp.bankrupt.model.db;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatuses {
  @Id
  Integer transactionStatusId;
  Integer transactionId;
  String status;
  LocalDateTime recorded;
}
