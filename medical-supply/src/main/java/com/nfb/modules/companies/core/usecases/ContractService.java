package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.ContractInfoDto;
import com.nfb.modules.companies.API.dtos.EquipmentInfoDto;
import com.nfb.modules.companies.core.domain.contract.Contract;
import com.nfb.modules.companies.core.domain.equipment.Equipment;
import com.nfb.modules.companies.core.repositories.ContractRepository;
import com.nfb.modules.companies.core.repositories.EquipmentRepository;
import com.nfb.modules.stakeholders.API.dtos.RecipientInfoDto;
import com.nfb.modules.stakeholders.core.domain.user.User;
import com.nfb.modules.stakeholders.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;

    public List<ContractInfoDto> getAllByCompanyId(long companyId) {
        List<Contract> contracts = contractRepository.findAllByCompanyId(companyId);

        return contracts.stream().map(this::mapToContractInfoDto).collect(Collectors.toList());
    }

    public ContractInfoDto getById(long contractId) {
        Contract contract = contractRepository.findById(contractId).orElse(null);
        if (contract == null) {
            return null;
        }
        return mapToContractInfoDto(contract);
    }

    private ContractInfoDto mapToContractInfoDto(Contract contract) {
        User user = userRepository.findById(contract.getUserId()).orElse(null);
        Equipment equipment = equipmentRepository.findById(contract.getEquipmentId()).orElse(null);

        RecipientInfoDto recipientInfoDto = null;
        if (user != null) {
            recipientInfoDto = new RecipientInfoDto(
                    user.getId(),
                    user.getUsername(),
                    user.getName(),
                    user.getSurname(),
                    user.getCity(),
                    user.getCountry(),
                    user.getPhoneNumber()
            );
        }

        EquipmentInfoDto equipmentInfoDto = null;
        if (equipment != null) {
            equipmentInfoDto = new EquipmentInfoDto(
                    equipment.getId(),
                    equipment.getName(),
                    equipment.getType(),
                    equipment.getDescription(),
                    equipment.getPrice(),
                    equipment.getCompanyEquipmentList().size()
            );
        }

        return new ContractInfoDto(
                contract.getId(),
                contract.getQuantity(),
                contract.getPickupDate(),
                contract.getStatus().toString(),
                equipmentInfoDto,
                recipientInfoDto
        );
    }
}
