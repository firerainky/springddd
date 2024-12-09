package com.zky.springddd.atm;

import org.springframework.data.repository.CrudRepository;

public interface AtmRepository extends CrudRepository<AtmDto, Long> {

}
