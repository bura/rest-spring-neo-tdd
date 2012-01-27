package org.bura.example.app.domain.repository;

import org.bura.example.app.domain.dto.Account;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

public interface AccountRepository extends GraphRepository<Account>,
		NamedIndexRepository<Account> {

}
