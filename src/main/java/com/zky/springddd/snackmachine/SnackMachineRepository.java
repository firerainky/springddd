package com.zky.springddd.snackmachine;

import org.springframework.data.repository.CrudRepository;

public interface SnackMachineRepository extends CrudRepository<SnackMachineDto, Long> { 
}
