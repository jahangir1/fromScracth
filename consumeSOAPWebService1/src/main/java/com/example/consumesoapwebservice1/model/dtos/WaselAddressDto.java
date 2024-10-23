package com.example.consumesoapwebservice1.model.dtos;

import java.io.Serializable;

public record WaselAddressDto(String identificationNumber, String fullName, Integer buildingNumber, Integer zipCode,
                              Integer unitNumber, Integer additionalNumber, String cityNameAr, String cityNameEn,
                              String districtAreaAr, String districtAreaEn) implements Serializable {
}
