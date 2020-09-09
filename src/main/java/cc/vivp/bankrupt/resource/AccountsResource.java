package cc.vivp.bankrupt.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.vivp.bankrupt.model.api.Account;
import cc.vivp.bankrupt.model.db.Accounts;
import cc.vivp.bankrupt.repository.AccountsRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "accounts")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class AccountsResource {

  private final AccountsRepository accountsRepository;

  @GetMapping("{accountId}")
  public Mono<Accounts> getById(@PathVariable int accountId) {
    return accountsRepository.findById(accountId);
  }

  @PostMapping
  public Mono<Account> create(@RequestBody Account account) {
    return accountsRepository.save(new Accounts(account)).map(Account::new);
  }

  @DeleteMapping("{accountId}")
  public Mono<Void> deleteById(@PathVariable int accountId) {
    return accountsRepository.deleteById(accountId);
  }

  @GetMapping("customers/{customerId}")
  public Flux<Account> getByCustomerId(@PathVariable String customerId) {
    return accountsRepository.findAllByCustomerId(customerId).map(Account::new);
  }
}
