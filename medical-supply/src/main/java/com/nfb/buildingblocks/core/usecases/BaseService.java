package com.nfb.buildingblocks.core.usecases;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseService<TDto, TDomain extends BaseEntity> {
    private final ModelMapper mapper;

    protected BaseService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    protected TDomain mapToDomain(TDto dto) {
        return mapper.map(dto, getDomainClass());
    }

    protected List<TDomain> mapToDomainList(List<TDto> dtos) {
        return dtos.stream().map(dto -> mapper.map(dto, getDomainClass())).collect(Collectors.toList());
    }

    protected TDto mapToDto(TDomain result) {
        return mapper.map(result, getDtoClass());
    }

    //Maybe we need to improve this method
    protected Optional<List<TDto>> mapToDto(Optional<List<TDomain>> result) {
        if (result.isEmpty()) {
            return Optional.empty();
        }

        List<TDto> dtos = result.get().stream()
                .map(domain -> mapper.map(domain, getDtoClass()))
                .collect(Collectors.toList());

        return Optional.of(dtos);
    }

    protected abstract Class<TDomain> getDomainClass();

    protected abstract Class<TDto> getDtoClass();
}
