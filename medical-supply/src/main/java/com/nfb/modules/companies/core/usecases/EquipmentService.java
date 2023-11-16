package com.nfb.modules.companies.core.usecases;

import com.nfb.buildingblocks.core.usecases.BaseService;
import com.nfb.modules.companies.API.dtos.EquipmentDto;
import com.nfb.modules.companies.API.serviceinterfaces.IEquipmentService;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import org.modelmapper.ModelMapper;

import java.util.List;

public class EquipmentService extends BaseService<EquipmentDto, Equipment> implements IEquipmentService {

    protected EquipmentService(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    protected Class<Equipment> getDomainClass() {
        return null;
    }

    @Override
    protected Class<EquipmentDto> getDtoClass() {
        return null;
    }

    @Override
    public List<EquipmentDto> Search(String name) {
        return null;
    }

    @Override
    public List<EquipmentDto> FilterByType(String type) {
        return null;
    }

    @Override
    public List<EquipmentDto> FilterByRating(double rating) {
        return null;
    }

    @Override
    public List<EquipmentDto> Filter(String type, double rating) {
        return null;
    }
}
