package com.sophos.poc.wsrestgestioncarrito.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sophos.poc.wsrestgestioncarrito.dto.CarritoDTO;

@Repository
public interface CarritoDTORepository extends CrudRepository<CarritoDTO, String>{

}
