package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.CreateEquipmentDto;
import com.nfb.modules.companies.API.dtos.EditEquipmentDto;
import com.nfb.modules.companies.API.dtos.EquipmentInfoDto;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.company.CompanyEquipment;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.CompanyEquipmentRepository;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.companies.core.repositories.EquipmentRepository;
import com.nfb.modules.companies.core.repositories.QREqipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final CompanyEquipmentRepository companyEquipmentRepository;
    private final CompanyRepository companyRepository;
    private final QREqipmentRepository qrEqipmentRepository;
    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository,
                            CompanyEquipmentRepository companyEquipmentRepository,
                            CompanyRepository companyRepository,
                            QREqipmentRepository qrEqipmentRepository) {
        this.equipmentRepository = equipmentRepository;
        this.companyEquipmentRepository = companyEquipmentRepository;
        this.companyRepository = companyRepository;
        this.qrEqipmentRepository = qrEqipmentRepository;
    }

    public Equipment add(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }
    public List<Equipment> getAll() { return equipmentRepository.findAll(); }
    public List<Equipment> filter(String name, String type, double minPrice, double maxPrice)
    {
        List<Equipment> filter = equipmentRepository.findFilteredEquipment(type, minPrice, maxPrice);
        List<Equipment> filterAndSearch = new ArrayList<>();

        for(Equipment e: filter) {
            if(e.getName().toLowerCase().contains(name.toLowerCase())) filterAndSearch.add(e);
        }

        return filterAndSearch;
    }
    public List<Equipment> search(String name) { return equipmentRepository.findByNameContainingIgnoreCase(name); }
    public List<Equipment> findByIdIn(List<Long> ids) { return equipmentRepository.findByIdIn(ids); }

    public Equipment getById(Long id) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(id);
        return equipmentOptional.orElse(null);
    }

    public List<Equipment> getByIds(List<Long> ids) {
        return equipmentRepository.findAllById(ids);
    }

    public void create(CreateEquipmentDto createEquipmentDto) {
        Equipment equipment = new Equipment(createEquipmentDto.getName(), createEquipmentDto.getType(), createEquipmentDto.getDescription(), createEquipmentDto.getPrice());
        equipmentRepository.save(equipment);

        Company company = companyRepository.findById(createEquipmentDto.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        CompanyEquipment companyEquipment = new CompanyEquipment(company, equipment, createEquipmentDto.getQuantity());

        companyEquipmentRepository.save(companyEquipment);

    }

    public boolean delete(Long id) {
        if (qrEqipmentRepository.existsByEquipment_Id(id)) {
            return false;
        }

        try {
            companyEquipmentRepository.deleteByEquipmentId(id);
            equipmentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void update(EditEquipmentDto editEquipmentDto) {
        equipmentRepository.update(editEquipmentDto.getId(), editEquipmentDto.getPrice(), editEquipmentDto.getDescription(), editEquipmentDto.getName(), editEquipmentDto.getType());
        companyEquipmentRepository.updateQuantity(editEquipmentDto.getId(), editEquipmentDto.getCompanyId(), editEquipmentDto.getQuantity());
    }

    public List<EquipmentInfoDto> searchByName(String name) {
        List<Equipment> equipments = equipmentRepository.findByNameContainingIgnoreCase(name);

        return equipments.stream().map(equipment -> {
            EquipmentInfoDto dto = new EquipmentInfoDto();
            dto.setId(equipment.getId());
            dto.setName(equipment.getName());
            dto.setType(equipment.getType());
            dto.setDescription(equipment.getDescription());
            dto.setPrice(equipment.getPrice());
            Optional<CompanyEquipment> companyEquipmentOptional = equipment.getCompanyEquipmentList()
                    .stream()
                    .filter(companyEquipment -> companyEquipment.getEquipment().getId() == equipment.getId())
                    .findFirst();
            if (companyEquipmentOptional.isPresent()) {
                CompanyEquipment companyEquipment = companyEquipmentOptional.get();
                dto.setQuantity(companyEquipment.getQuantity());
            } else {
                dto.setQuantity(0);
            }
            return dto;
        }).collect(Collectors.toList());
    }
}
