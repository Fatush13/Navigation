package ee.task.nagivation.data;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MobileStation {
   @Id
   @Column (name = "id", updatable = false, nullable = false, unique = true)
   UUID id;
   float lastKnownX;
   float lastKnownY;
   float errorRadius;

}
