package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.dto.CreatePerformanceDTO;
import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.entity.Hall;
import cz.muni.fi.pa165.entity.Performance;
import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.services.HallService;
import cz.muni.fi.pa165.services.PerformanceService;

/**
 *
 * @author xtrnkal
 */
public class PerformanceFacadeImpl implements PerformanceFacade {

    @Inject
    private PerformanceService performanceService;

    @Inject
    private HallService hallService;

    @Inject
    private BeanMappingService mappingService;

    @Override
    public Long createPerformance(CreatePerformanceDTO newPerformance) {
        Performance mappedPerformance = mappingService.mapTo(newPerformance, Performance.class);
        mappedPerformance.setHall(hallService.findById(newPerformance.getHallId()));
        performanceService.create(mappedPerformance);
        return mappedPerformance.getId();
    }

    @Override
    public List<PerformanceDTO> getAllPerformances() {
        return mappingService.mapTo(performanceService.findAll(), PerformanceDTO.class);
    }

    @Override
    public PerformanceDTO getPerformanceById(Long id) {
        return mappingService.mapTo(performanceService.findById(id), PerformanceDTO.class);
    }

    @Override
    public void removePerformance(PerformanceDTO performance) {
        performanceService.remove(mappingService.mapTo(performance, Performance.class));
    }

    @Override
    public void updatePerformance(PerformanceDTO performance) {
        performanceService.update(mappingService.mapTo(performance, Performance.class));
    }

    @Override
    public List<PerformanceDTO> getAllPerfomancesByHallId(Long hallId) {
        Hall hall = hallService.findById(hallId);
        return mappingService.mapTo(performanceService.findAllByHall(hall), PerformanceDTO.class);
    }

}
