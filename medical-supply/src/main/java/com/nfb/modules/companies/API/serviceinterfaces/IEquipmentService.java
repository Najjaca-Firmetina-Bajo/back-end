package com.nfb.modules.companies.API.serviceinterfaces;

import com.nfb.modules.companies.API.dtos.EquipmentDto;
import java.util.List;

public interface IEquipmentService {
    public List<EquipmentDto> Search(String name);
    public List<EquipmentDto> FilterByType(String type);
    public List<EquipmentDto> FilterByRating(double rating);
    public List<EquipmentDto> Filter(String type, double rating);
}
