package com.spring.mvc.repository;

import java.util.List;

import com.spring.mvc.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    //поиск заявок по ключевым словам
    @Query(value = "SELECT c FROM Client c WHERE c.name LIKE '%' || :keyword || '%'"
            + " OR c.serviceType LIKE '%' || :keyword || '%'"
            + " OR c.dateVisit LIKE '%' || :keyword || '%'")
    public List<Client> search(@Param("keyword") String keyword);

}
