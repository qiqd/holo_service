package org.holo.repo;

import org.holo.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
  List<Account> queryByEmail(String email);

  List<Account> findByIdAndEmail(String id, String email);

  List<Account> findByEmail(String email);
}
