package br.ufes.inf.nemo.marvin.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Course;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface CourseDAO extends BaseDAO<Course>{

}
