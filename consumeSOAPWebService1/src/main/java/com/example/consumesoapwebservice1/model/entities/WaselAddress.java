package com.example.consumesoapwebservice1.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wasel_address_tmp")
public class WaselAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identification_number", nullable = false, length = 10)
    private String identificationNumber;

    @Column(name = "full_name", length = 80)
    private String fullName;

    @Column(name = "building_number")
    private Integer buildingNumber;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(name = "unit_number")
    private Integer unitNumber;

    @Column(name = "additional_number")
    private Integer additionalNumber;

    @Column(name = "city_name_ar", length = 100)
    private String cityNameArabic;

    @Column(name = "city_name_en", length = 100)
    private String cityNameEnglish;

    @Column(name = "district_area_ar", length = 100)
    private String districtAreaArabic;

    @Column(name = "district_area_en", length = 100)
    private String districtAreaEnglish;

    @Column(name = "street_name_ar", length = 100)
    private String streetNameArabic;

    @Column(name = "street_name_en", length = 100)
    private String streetNameEnglish;

}