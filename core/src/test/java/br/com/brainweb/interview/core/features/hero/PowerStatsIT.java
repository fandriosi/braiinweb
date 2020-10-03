package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("it")
public class PowerStatsIT {
    @Test
    public void Test(){
        PowerStats stats = new PowerStats();
        stats.getAgility();
        PowerStatsService service = new PowerStatsService();
        service.savePowerStats(stats);
    }
}
