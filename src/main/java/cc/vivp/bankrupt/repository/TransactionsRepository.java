package cc.vivp.bankrupt.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import cc.vivp.bankrupt.model.db.Transactions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionsRepository extends ReactiveCrudRepository<Transactions, Integer> {

  @Query("SELECT * FROM public.transactions WHERE transaction_reference = $1")
  Mono<Transactions> findByTransactionReference(String transactionReference);

  @Query("SELECT * FROM public.transactions WHERE source = $1")
  Flux<Transactions> findBySource(int source);

  @Query("SELECT * FROM public.transactions WHERE target = $1")
  Flux<Transactions> findByTarget(int target);
}
