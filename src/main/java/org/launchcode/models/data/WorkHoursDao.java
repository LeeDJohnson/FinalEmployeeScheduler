package org.launchcode.models.data;

import org.launchcode.models.WorkHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WorkHoursDao extends CrudRepository<WorkHours, Integer>{
}