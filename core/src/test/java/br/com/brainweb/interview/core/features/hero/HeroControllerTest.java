package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerTest {
    @Autowired
    private PowerStatsRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private HeroRepository heroRepository;
    @Test
    public void testControllerHeroA() throws Exception{
        PowerStats model = new PowerStats();
        model.setStrength(7);
        model.setAgility(6);
        model.setDexterity(5);
        model.setIntelligence(4);
        model.setCreated_at(new Date(2020,02,02));
        model.setUpdated_at(new Date(2020,02,04));
        PowerStats stats =repository.save(model);
        Hero hero = new Hero();
        hero.setPowerStats(stats);
        hero.setName("Volverine");
        hero.setRace(Race.HUMAN.name());
        hero.setEnabled(true);
        hero.setCreated_at(new Date(2020,9,04));
        hero.setUpdated_at(new Date(2020,9,04));
        String json = StrJson.getJson("");
        System.out.println(json);
        mockMvc.perform(MockMvcRequestBuilders.post("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.power_stats.strength", is(4)))
                .andExpect(jsonPath("$.power_stats.agility", is(4)))
                .andExpect(jsonPath("$.power_stats.dexterity", is(4)))
                .andExpect(jsonPath("$.power_stats.intelligence", is(4)))
                .andExpect(jsonPath("$.race", is(Race.HUMAN.name())))
                .andExpect(jsonPath("$.name", is("Volverine")));
    }

    @Test
    public void testControllerHeroB() throws Exception{
        PowerStats model = new PowerStats();
        model.setStrength(7);
        model.setAgility(6);
        model.setDexterity(5);
        model.setIntelligence(4);
        model.setCreated_at(new Date(2020,02,02));
        model.setUpdated_at(new Date(2020,02,04));
        PowerStats stats =repository.save(model);
        Hero hero = new Hero();
        hero.setName("Volverine");
        hero.setPowerStats(stats);
        hero.setRace(Race.HUMAN.name());
        hero.setEnabled(true);
        hero.setCreated_at(new Date(2020,9,04));
        hero.setUpdated_at(new Date(2020,9,04));
        String json = StrJson.getJson(hero);
        mockMvc.perform(MockMvcRequestBuilders.post("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.power_stats.strength", is(4)))
                .andExpect(jsonPath("$.power_stats.agility", is(4)))
                .andExpect(jsonPath("$.power_stats.dexterity", is(3)))
                .andExpect(jsonPath("$.power_stats.intelligence", is(4)))
                .andExpect(jsonPath("$.race", is(Race.HUMAN.name())))
                .andExpect(jsonPath("$.name", is("Volverine")));
    }
    @Test
    public void testControllerHeroC() throws Exception{
        UUID id = UUID.randomUUID();
        Hero hero = new Hero();
        hero.setId(id);
        mockMvc.perform(MockMvcRequestBuilders.get("/hero/"+hero.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testControllerHeroD() throws Exception{
        Hero hero = this.createThor();
        mockMvc.perform(MockMvcRequestBuilders.get("/findByName".concat("/").concat(hero.getName()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testControllerHeroF() throws Exception{
        Hero hero = this.createThor();
        Hero hero1 = this.creatCaptao();
        mockMvc.perform(MockMvcRequestBuilders.get("/hero/"+hero.getId()+"/"+hero1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testControllerHeroG() throws Exception{
        Hero hero = this.createThor();
        hero.setName("Ragnaroque");
        String json = StrJson.getJson(hero);
        mockMvc.perform(MockMvcRequestBuilders.put("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
    @Test
    public void testControllerHeroH() throws Exception{
        Hero hero = this.createThor();
        String json = StrJson.getJson(hero);
        mockMvc.perform(MockMvcRequestBuilders.delete("/hero0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
    private Hero createThor(){
        Hero hero = new Hero();
        PowerStats heroPower = new PowerStats();
        heroPower.setAgility(4);
        heroPower.setStrength(10);
        heroPower.setDexterity(8);
        heroPower.setIntelligence(8);
        heroPower.setCreated_at(new Date(2020,9,05));
        heroPower.setUpdated_at(new Date(2020,9,05));
        PowerStats stats = repository.save(heroPower);
        hero.setName("Thor");
        hero.setRace(Race.DIVINE.name());
        hero.setPowerStats(heroPower);
        hero.setEnabled(true);
        hero.setCreated_at(new Date(2020,9,05));
        hero.setCreated_at(new Date(2020,9,05));
        return heroRepository.save(hero);
    }
    private Hero creatCaptao(){
        Hero hero = new Hero();
        PowerStats heroPower = new PowerStats();
        heroPower.setAgility(4);
        heroPower.setStrength(7);
        heroPower.setDexterity(8);
        heroPower.setIntelligence(9);
        heroPower.setCreated_at(new Date(2020,9,05));
        heroPower.setUpdated_at(new Date(2020,9,05));
        PowerStats stats = repository.save(heroPower);
        hero.setName("Capitao Americao");
        hero.setRace(Race.HUMAN.name());
        hero.setPowerStats(heroPower);
        hero.setEnabled(true);
        hero.setCreated_at(new Date(2020,9,05));
        hero.setCreated_at(new Date(2020,9,05));
        return heroRepository.save(hero);
    }
}
