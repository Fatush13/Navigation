package ee.task.nagivation.data;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;


@Entity
@NoArgsConstructor
@Data
public class BaseStation {
   @Id
   @GeneratedValue (generator = "UUID")
   @GenericGenerator (name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
   @Column (name = "id", updatable = false, nullable = false, unique = true)
   UUID id;
   String name;
   @NotNull
   Float x;
   @NotNull
   Float y;
   @NotNull
   Float detectionRadiusInMeters;

}
