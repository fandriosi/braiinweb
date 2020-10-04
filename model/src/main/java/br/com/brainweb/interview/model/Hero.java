package br.com.brainweb.interview.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Entity
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Length(max=255)
    private String name;
    @NotNull
    @Length(max=255)
    private String race;
    @NotNull
    @OneToOne
    @JoinColumn(name = "power_stats_id")
    private PowerStats powerStats;
    @NotNull
    @ColumnDefault(value = "TRUE")
    private Boolean enabled;
    @NotNull
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDate created_at;
    @NotNull
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDate updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public PowerStats getPowerStats() {
        return powerStats;
    }

    public void setPowerStats(PowerStats powerStats) {
        this.powerStats = powerStats;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }
}
