package com.supermarket.billing.repositories;

import com.supermarket.billing.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {

}
