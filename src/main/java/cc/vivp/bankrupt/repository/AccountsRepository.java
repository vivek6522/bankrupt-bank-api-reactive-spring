package cc.vivp.bankrupt.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import cc.vivp.bankrupt.model.db.Accounts;
import reactor.core.publisher.Flux;

@Repository
public interface AccountsRepository extends ReactiveCrudRepository<Accounts, Integer> {

  @Query("SELECT * FROM public.accounts WHERE customer_id = $1 ORDER BY account_id ASC")
  Flux<Accounts> findAllByCustomerId(String customerId);
}
