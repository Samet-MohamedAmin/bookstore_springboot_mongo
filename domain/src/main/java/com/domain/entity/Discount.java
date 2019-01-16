package com.domain.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "discount")
public class Discount implements GeneralEntity {
    @Id
    private String id;
    private double percentage;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    /*
     * verify if the combination of dateStart and dateEnd is valid
     * ie. dateStart must be before dateEnd
     * this is helpful when validating discount input before saving in database
     *
     */
    @JsonIgnore
    public boolean isDateValid() {

        return dateStart.isBefore(dateEnd);
    }

    /*
     * verify if the discount is expired or ended
     * ie. the current date is after dateEnd
     * we will need this when using cron to periodically clear expired discounts
     *
     */
    @JsonIgnore
    public boolean isExpired() {

        return LocalDate.now().isAfter(dateEnd);
    }

    /*
     * verify if the discount is active
     * ie. current date is between dateStart and dateEnd
     * there is NO discount when discount is not active
     *
     */
    @JsonIgnore
    boolean isActive(){

        return LocalDate.now().isAfter(dateStart) && LocalDate.now().isBefore(dateEnd);
    }
}
