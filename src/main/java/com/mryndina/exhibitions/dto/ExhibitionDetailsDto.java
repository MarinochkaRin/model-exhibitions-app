package com.mryndina.exhibitions.dto;

import com.mryndina.exhibitions.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionDetailsDto {
    private int id;
    private String theme;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private List<Location> locations;
    private double ticketPrice;
}
