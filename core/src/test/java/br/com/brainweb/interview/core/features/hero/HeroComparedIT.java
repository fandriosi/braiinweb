package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class HeroComparedIT {
    @Autowired
    private PowerStatsRepository powerStatsRepository;
    @Autowired
    private HeroRepository heroRepository;

}
