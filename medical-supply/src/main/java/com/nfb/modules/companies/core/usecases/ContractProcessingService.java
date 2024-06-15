package com.nfb.modules.companies.core.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfb.config.RabbitMQConfig;
import com.nfb.modules.companies.API.dtos.ContractDto;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.domain.contract.Contract;
import com.nfb.modules.companies.core.domain.contract.ContractStatus;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.companies.core.repositories.ContractRepository;
import com.nfb.modules.stakeholders.core.domain.user.User;
import com.nfb.modules.stakeholders.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractProcessingService {

    private final ContractRepository contractRepository;


    @Autowired
    private ObjectMapper objectMapper;
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processContract(String contractDtoJson) throws IOException {
        ContractDto contractDto = objectMapper.readValue(contractDtoJson, ContractDto.class);
        Contract newContract = new Contract(contractDto.getUserId(), contractDto.getCompanyId(), contractDto.getEquipmentId(),
                contractDto.getQuantity(), contractDto.getPickupDate(), ContractStatus.VALID);


        Optional<Contract> existingContractOptional = contractRepository.findByUserIdAndCompanyIdAndStatus(
                contractDto.getUserId(),
                contractDto.getCompanyId(),
                ContractStatus.VALID
        );

        if (existingContractOptional.isPresent()) {
            Contract existingContract = existingContractOptional.get();
            existingContract.setStatus(ContractStatus.INVALID);
            contractRepository.save(existingContract);
        }

        contractRepository.save(newContract);
    }
}