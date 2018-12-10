package cz.muni.fi.pa165;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"cz.muni.fi.pa165.services", "cz.muni.fi.pa165.facade"})
@Import(ApplicationContext.class)
public class ServicesContext {
	@Bean
	public Mapper mapper() {
		return new DozerBeanMapper();
	}
}
