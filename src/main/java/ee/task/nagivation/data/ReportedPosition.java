package ee.task.nagivation.data;


import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportedPosition {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    UUID mobileStationId;
    float x;
    float y;
    float errorRadius;
    Instant timestamp;

}
