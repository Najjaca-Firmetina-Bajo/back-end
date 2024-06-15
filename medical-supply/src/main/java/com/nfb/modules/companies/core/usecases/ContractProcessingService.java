package com.nfb.modules.companies.core.usecases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfb.config.RabbitMQConfig;
import com.nfb.modules.companies.API.dtos.ContractDto;
import com.nfb.modules.companies.core.domain.contract.Contract;
import com.nfb.modules.companies.core.domain.contract.ContractStatus;
import com.nfb.modules.companies.core.repositories.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        contractRepository.save(newContract);
        System.out.println("Primljen je novi ugovor: " + contractDto);
    }
}