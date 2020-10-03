package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;


public class PowerStatsService {
    @Autowired
    private PowerStatsRepository repository;
    public void savePowerStats(PowerStats stats){
        repository.save(stats);
    }
}
