package com.nfb.modules.companies.core.usecases;

import com.nfb.modules.companies.API.dtos.CompanyDto;
import com.nfb.modules.companies.API.dtos.EditCompanyDto;
import com.nfb.modules.companies.core.domain.appointment.Appointment;
import com.nfb.modules.companies.core.domain.appointment.QRCode;
import com.nfb.modules.companies.core.domain.company.Company;
import com.nfb.modules.companies.core.repositories.CompanyRepository;
import com.nfb.modules.companies.core.repositories.QRCodeRepository;
import com.nfb.modules.companies.core.repositories.WorkingDayRepository;
import com.nfb.modules.stakeholders.core.domain.user.CompanyAdministrator;
import com.nfb.modules.stakeholders.core.usecases.CompanyAdministratorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService   {
    private final CompanyRepository companyRepository;
    private final EquipmentService equipmentService;
    private final CompanyAdministratorService companyAdministratorService;
    private final WorkingDayRepository workingDayRepository;
    private final QRCodeRepository qrCodeRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, EquipmentService equipmentService, CompanyAdministratorService companyAdministratorService, WorkingDayRepository workingDayRepository, QRCodeRepository qrCodeRepository) {
        this.companyRepository = companyRepository;
        this.equipmentService = equipmentService;
        this.companyAdministratorService = companyAdministratorService;
        this.workingDayRepository = workingDayRepository;
        this.qrCodeRepository = qrCodeRepository;
    }

    public Company register(Company company) { return companyRepository.save(company); }
    public List<Company> getAll() { return companyRepository.findAll(); }
    public Company prepareCompanyModel(Long companyId, String name, String address, double rating)
    {
        Company company = companyRepository.findById(companyId).orElse(null);
        List<CompanyAdministrator> admins = companyAdministratorService.findByCompany(company);
        return new Company(name, address, rating, admins);
    }
    public List<Company> findByIdIn(List<Long> ids) { return companyRepository.findByIdIn(ids); }
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public Company findOneById(Long id) {
        return companyRepository.findOneById(id);
    }

    @Transactional
    public Company addAdministratorToCompany(long companyId, long administratorId)
    {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company not found."));
        CompanyAdministrator administrator = companyAdministratorService.findById(administratorId).orElseThrow(() -> new EntityNotFoundException("Company administrator not found."));

        List<CompanyAdministrator> administrators = company.getAdministrators();
        administrators.add(administrator);
        companyRepository.addAdministratorToCompany(companyId, administrators);

        return companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company not found."));
    }

    public Company findByName(String name) { return companyRepository.findByName(name); }

    public List<Company> search(String nameOrPlace){
        return companyRepository.findByNameIgnoreCaseOrAddressContainingIgnoreCase(nameOrPlace, nameOrPlace);
    }

    public List<Company> filter(String nameOrPlace, double rating){
        List<Company> filter = companyRepository.findCompaniesByAverageRating(rating);
        return getCompanies(nameOrPlace, filter);
    }

    public List<Company> filterByEquipmentCount(String nameOrPlace,int equipmentCount) {
        List<Company> filter = companyRepository.filterByEquipmentCount(equipmentCount);
        return getCompanies(nameOrPlace, filter);
    }

    private List<Company> getCompanies(String nameOrPlace, List<Company> filter) {
        List<Company> filterAndSearch = new ArrayList<>();

        for(Company c: filter) {
            if(c.getName().equals(nameOrPlace)) filterAndSearch.add(c);
            else if(c.getAddress().toLowerCase().contains(nameOrPlace.toLowerCase())) filterAndSearch.add(c);
        }

        return filterAndSearch;
    }

    public boolean checkIfCompanyIsWorking(long companyId, Date date){
        if(workingDayRepository.checkIfCompanyIsWorking(companyId,date) == null) return false;
        return true;
    }

    public List<CompanyAdministrator> getCompanyAdministrators(long companyId){
        return companyRepository.getCompanyAdministrators(companyId);
    }

    public List<Company> sortCompanies(String ascOrDesc, String type){
        if(type.equals("name")){
            if(ascOrDesc.equals("asc")) return companyRepository.sortCompaniesByNameAsc();
            return companyRepository.sortCompaniesByNameDesc();
        }
        else if(type.equals("address")){
            if(ascOrDesc.equals("asc")) return companyRepository.sortCompaniesByAddressAsc();
            return companyRepository.sortCompaniesByAddressDesc();
        }
        else if(type.equals("rating")){
            if(ascOrDesc.equals("asc")) return companyRepository.sortCompaniesByRatingAsc();
            return companyRepository.sortCompaniesByRatingDesc();
        }
        return null;
    }

    public Boolean CheckIfUserCanRateComp(long userId, long companyId) {
        List<QRCode> usersQRCodes = qrCodeRepository.findByRegisteredUser_Id(userId);
        List<CompanyAdministrator> companyAdministrators = companyRepository.getCompanyAdministrators(companyId);
        List<Appointment> appointments = new ArrayList<>();

        for (QRCode q :
                usersQRCodes) {
            appointments.add(q.getAppointment());
        }

        for (Appointment a :
                appointments) {
            for (CompanyAdministrator ca :
                    companyAdministrators) {
                if(a.getCompanyAdministrator() == ca) return true;
            }
        }

        return false;
    }

    public Company getById(Long id) {
        return companyRepository.getById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void updateInfo(EditCompanyDto companyDto) {
        companyRepository.updateInfo(companyDto.getId(), companyDto.getName(), companyDto.getAddress(), companyDto.getDescription(), companyDto.getAverageRating());
    }
}
