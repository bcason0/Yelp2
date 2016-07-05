package io.yelp.repo;

import io.yelp.domain.Business;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bryantcason on 7/5/16.
 */
public interface BusinessRepo extends CrudRepository<Business, Long>{
}
