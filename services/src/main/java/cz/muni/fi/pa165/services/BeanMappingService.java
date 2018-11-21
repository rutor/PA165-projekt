package cz.muni.fi.pa165.services;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;


public interface BeanMappingService {
<<<<<<< HEAD

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);

    public Mapper getMapper();
}
=======
	
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
>>>>>>> milestone2/integration
