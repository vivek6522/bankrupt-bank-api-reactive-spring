package cc.vivp.bankrupt.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import cc.vivp.bankrupt.model.db.TransactionStatuses;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionStatusesRepository extends ReactiveCrudRepository<TransactionStatuses, Integer> {

  @Query("SELECT * FROM public.transaction_statuses WHERE transaction_id = $1")
  Flux<TransactionStatuses> findByTransactionId(int transactionId);
}
