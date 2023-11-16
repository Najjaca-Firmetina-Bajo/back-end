package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.stakeholders.core.domain.user.Patike;
import com.nfb.modules.stakeholders.core.repositories.PatikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatikeService {

    private final PatikeRepository patikeRepository;

    @Autowired
    public PatikeService(PatikeRepository patikeRepository) {
        this.patikeRepository = patikeRepository;
    }

    // Save a new Patike
    public Patike savePatike(Patike patike) {
        return patikeRepository.save(patike);
    }

    // Get all Patike entities
    public List<Patike> getAllPatike() {
        return patikeRepository.findAll();
    }

    // Get Patike by ID
    public Optional<Patike> getPatikeById(Long id) {
        return patikeRepository.findById(id);
    }

    // Update existing Patike
    public Patike updatePatike(Long id, Patike updatedPatike) {
        Optional<Patike> existingPatike = patikeRepository.findById(id);

        if (existingPatike.isPresent()) {
            Patike patike = existingPatike.get();
            patike.setBrand(updatedPatike.getBrand());
            patike.setModel(updatedPatike.getModel());
            patike.setSize(updatedPatike.getSize());

            return patikeRepository.save(patike);
        } else {
            // Handle not found scenario
            // You might want to throw an exception or handle it based on your application's requirements
            return null;
        }
    }

    // Delete Patike by ID
    public void deletePatikeById(Long id) {
        patikeRepository.deleteById(id);
    }
}

