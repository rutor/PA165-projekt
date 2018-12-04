package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.ServicesContext;
import cz.muni.fi.pa165.services.TicketService;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
@Import(ServicesContext.class)
public class FacadesContext {

	@Primary
	@Bean
	public TicketService getTicketService() {
		return Mockito.mock(TicketService.class);
	}
}
