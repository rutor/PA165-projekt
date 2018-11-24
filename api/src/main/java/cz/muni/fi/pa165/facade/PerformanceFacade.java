package cz.muni.fi.pa165.facade;

import java.util.List;
import cz.muni.fi.pa165.dto.PerformanceDTO;
import cz.muni.fi.pa165.dto.CreatePerformanceDTO;

/**
 *
 * @author xtrnkal
 */
public interface PerformanceFacade {

    public Long createPerformance(CreatePerformanceDTO newPerformance);

    public List<PerformanceDTO> getAllPerformances();

    public PerformanceDTO getPerformanceById(Long id);

    public List<PerformanceDTO> getAllPerfomancesByHallId(Long hallId);

    public void removePerformance(PerformanceDTO performance);

    public void updatePerformance(PerformanceDTO performance);
}
