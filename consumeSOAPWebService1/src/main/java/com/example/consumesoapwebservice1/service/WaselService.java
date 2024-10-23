package com.example.consumesoapwebservice1.service;

import com.example.consumesoapwebservice1.model.dtos.WaselAddressDto;
import com.example.consumesoapwebservice1.model.entities.WaselAddress;
import com.example.consumesoapwebservice1.model.repos.WaselAddressRepository;
import com.example.consumesoapwebservice1.stubs.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaselService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    WaselAddressRepository waselAddressRepository;

    private WaselAddressDto convertToDto(WaselAddress entity) {
        WaselAddressDto waselAddressDto = modelMapper.map(entity, WaselAddressDto.class);
        return waselAddressDto;
    }

    private WaselAddress convertToEntity(WaselAddressStructure waselAddressDto) {
        WaselAddress waselAddress = modelMapper.map(waselAddressDto, WaselAddress.class);
        return waselAddress;
    }

    public void saveAddress(GetIndividualWaselAddressResponse obj, String identifier) {
        System.out.println("Inside >> saveAddress");
        GetIndividualWaselAddressResponseStructure addressResponseStructure = obj.getGetIndividualWaselAddressResult();
        WaselAddressesListStructure retrievedAddress = addressResponseStructure.getGetIndividualWaselAddressResponseDetailObject();
        CommonErrorStructure serviceError = addressResponseStructure.getServiceError();

        if(retrievedAddress!=null){
            System.out.println("retrievedAddress.getWaselAddress().size() >> " + retrievedAddress.getWaselAddress().size());
            List<WaselAddressStructure> waselAddressList = retrievedAddress.getWaselAddress();
            waselAddressList.stream()
                    .forEach(address -> addRecordInDB(address, identifier));

        }

        if(serviceError!=null){
            System.out.println("serviceError.getCode().toString() >> " + serviceError.getCode().toString());
        }

    }

    private void addRecordInDB(WaselAddressStructure address, String identifier) {
        Optional<WaselAddress> isAddressFound = waselAddressRepository.findFirstByIdentificationNumberAndBuildingNumberAndUnitNumberAndZipCode(identifier, address.getBuildingNumber(), address.getUnitNumber(), address.getZipCode());
        System.out.println("present >> " + isAddressFound.isPresent());
        if(!isAddressFound.isPresent()){
            WaselAddress waselAddress = convertToEntity(address);
            waselAddress.setIdentificationNumber(identifier);
            waselAddressRepository.save(waselAddress);
        }
    }
}

