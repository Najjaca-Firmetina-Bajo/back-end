package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.AppointmentInfoDto;
import com.nfb.modules.companies.API.dtos.ReservationInfoDto;
import com.nfb.modules.companies.core.domain.appointment.QREquipment;
import com.nfb.modules.companies.core.domain.appointment.QRStatus;
import com.nfb.modules.companies.core.repositories.QREqipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QREquipmentService {

    private QREqipmentRepository qrEqipmentRepository;

    @Autowired
    public QREquipmentService(QREqipmentRepository qrEqipmentRepository) {
        this.qrEqipmentRepository = qrEqipmentRepository;
    }

    public List<QREquipment> getAll() { return qrEqipmentRepository.findAll(); }

    public List<ReservationInfoDto> getAllByAdminId(Long id) {
        List<QREquipment> qrEquipments = qrEqipmentRepository.findAllByCompanyAdministratorId(id);

        List<ReservationInfoDto> reservationInfoDtos = qrEquipments.stream().map(qrEquipment -> {
            ReservationInfoDto dto = new ReservationInfoDto();
            dto.setId(qrEquipment.getId());
            dto.setEquipmentId(qrEquipment.getEquipmentId());
            dto.setEquipmentName(qrEquipment.getEquipment().getName());
            dto.setQrCodeId(qrEquipment.getQrCodeID());
            dto.setStatus(qrEquipment.getQrCode().getStatus().toString());
            dto.setQuantity(qrEquipment.getQuantity());
            dto.setAppointmentId(qrEquipment.getQrCode().getAppointment().getId());
            dto.setPickUpDate(qrEquipment.getQrCode().getAppointment().getPickUpDate());
            dto.setRecipientId(qrEquipment.getQrCode().getRegisteredUser().getId());
            dto.setRecipientUsername(qrEquipment.getQrCode().getRegisteredUser().getUsername());
            dto.setRecipientName(qrEquipment.getQrCode().getRegisteredUser().getName());
            dto.setRecipientSurname(qrEquipment.getQrCode().getRegisteredUser().getSurname());
            return dto;
        }).collect(Collectors.toList());


        return reservationInfoDtos;
    }

    public List<ReservationInfoDto> getAllNewReservations(Long id) {
        List<QREquipment> qrEquipments = qrEqipmentRepository.findAllByCompanyAdministratorIdAndStatusNowAndFuturePickupDate(id);

        List<ReservationInfoDto> reservationInfoDtos = qrEquipments.stream().map(qrEquipment -> {
            ReservationInfoDto dto = new ReservationInfoDto();
            dto.setId(qrEquipment.getId());
            dto.setEquipmentId(qrEquipment.getEquipmentId());
            dto.setEquipmentName(qrEquipment.getEquipment().getName());
            dto.setQrCodeId(qrEquipment.getQrCodeID());
            dto.setStatus(qrEquipment.getQrCode().getStatus().toString());
            dto.setQuantity(qrEquipment.getQuantity());
            dto.setAppointmentId(qrEquipment.getQrCode().getAppointment().getId());
            dto.setPickUpDate(qrEquipment.getQrCode().getAppointment().getPickUpDate());
            dto.setRecipientId(qrEquipment.getQrCode().getRegisteredUser().getId());
            dto.setRecipientUsername(qrEquipment.getQrCode().getRegisteredUser().getUsername());
            dto.setRecipientName(qrEquipment.getQrCode().getRegisteredUser().getName());
            dto.setRecipientSurname(qrEquipment.getQrCode().getRegisteredUser().getSurname());
            return dto;
        }).collect(Collectors.toList());


        return reservationInfoDtos;
    }
}
