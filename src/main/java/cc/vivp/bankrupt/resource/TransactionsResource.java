package cc.vivp.bankrupt.resource;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.vivp.bankrupt.model.api.Transaction;
import cc.vivp.bankrupt.model.api.TransactionStatus;
import cc.vivp.bankrupt.model.db.TransactionStatuses;
import cc.vivp.bankrupt.model.db.Transactions;
import cc.vivp.bankrupt.repository.TransactionStatusesRepository;
import cc.vivp.bankrupt.repository.TransactionsRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "transactions")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class TransactionsResource {

  private final TransactionsRepository transactionsRepository;
  private final TransactionStatusesRepository transactionStatusesRepository;

  @GetMapping("{transactionId}")
  public Mono<Transaction> getSingle(@PathVariable int transactionId) {
    return transactionsRepository.findById(transactionId).map(Transaction::new);
  }

  @GetMapping("source/{source}")
  public Flux<Transaction> getBySource(@PathVariable int source) {
    return transactionsRepository.findBySource(source).map(Transaction::new);
  }

  @GetMapping("target/{target}")
  public Flux<Transaction> getByTarget(@PathVariable int target) {
    return transactionsRepository.findByTarget(target).map(Transaction::new);
  }

  @PostMapping
  @Transactional
  public Mono<Transaction> create(@RequestBody Transaction transaction) {
    String reference = UUID.randomUUID().toString();
    transaction.setTransactionReference(reference);
    return transactionsRepository.save(new Transactions(transaction)).map(Transaction::new)
        .doOnSuccess(newTransaction -> this.transactionStatusesRepository
            .save(new TransactionStatuses(null, newTransaction.getTransactionId(), "PROCESS_SOURCE", null))
            .subscribe());
  }

  @GetMapping("{transactionId}/status")
  public Flux<TransactionStatus> getStatus(@PathVariable int transactionId) {
    return transactionStatusesRepository.findByTransactionId(transactionId).map(TransactionStatus::new);
  }
}
