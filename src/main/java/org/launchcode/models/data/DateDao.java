package org.launchcode.models.data;

import org.launchcode.models.Date;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface DateDao extends CrudRepository<Date, Integer>{
}


