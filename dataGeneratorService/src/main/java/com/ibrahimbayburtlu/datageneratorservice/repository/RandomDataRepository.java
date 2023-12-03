package com.ibrahimbayburtlu.datageneratorservice.repository;

import com.ibrahimbayburtlu.datageneratorservice.entity.RandomData;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RandomDataRepository extends CouchbaseRepository<RandomData, String> {

}
