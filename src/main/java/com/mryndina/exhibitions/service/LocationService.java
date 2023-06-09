package com.mryndina.exhibitions.service;

import com.mryndina.exhibitions.entity.Location;
import com.mryndina.exhibitions.repository.ExhibitionRepository;
import com.mryndina.exhibitions.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class LocationService {
    private final int PAGE_SIZE = 10;

    private final LocationRepository locationRepository;
    private final ExhibitionRepository exhibitionRepository;

    public LocationService(LocationRepository locationRepository, ExhibitionRepository exhibitionRepository) {
        this.locationRepository = locationRepository;
        this.exhibitionRepository = exhibitionRepository;
    }

    public List<Location> checkAvailability(LocalDate startDate, LocalDate endDate) {
        List<Location> freeLocations = locationRepository.findAll();
        Set<Location> occupiedLocations = exhibitionRepository.findOccupiedLocations(startDate, endDate);
        freeLocations.removeAll(occupiedLocations);
        return freeLocations;
    }

    public void createLocation(Location location) {
        locationRepository.save(location);
        log.info("Location created: {}", location.getName());
    }

    public Page<Location> getAllLocations(int page) {
        return locationRepository.findAll(PageRequest.of(page, PAGE_SIZE));
    }
}
