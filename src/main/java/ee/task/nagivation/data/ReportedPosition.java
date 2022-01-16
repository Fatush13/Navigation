package ee.task.nagivation.data;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
public class ReportedPosition {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    UUID mobileStationId;
    float x;
    float y;
    float errorRadius;
    LocalDateTime timeStamp;

}
