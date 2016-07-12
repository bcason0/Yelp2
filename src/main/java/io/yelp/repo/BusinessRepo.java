package io.yelp.repo;

import io.yelp.domain.Business;
import org.springframework.data.repository.CrudRepository;

public interface BusinessRepo extends CrudRepository<Business, Long>{
}
