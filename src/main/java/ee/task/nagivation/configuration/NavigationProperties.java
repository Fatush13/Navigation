package ee.task.nagivation.configuration;

import ee.task.nagivation.data.BaseStation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "navigation")
@Setter
@Getter
public class NavigationProperties {
    private List<BaseStation> baseStations;
    @DurationUnit(ChronoUnit.MILLIS)
    private Duration dataExpirationTime;

}
