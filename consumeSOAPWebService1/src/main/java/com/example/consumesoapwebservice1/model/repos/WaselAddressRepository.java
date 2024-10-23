package com.example.consumesoapwebservice1.model.repos;

import com.example.consumesoapwebservice1.model.entities.WaselAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaselAddressRepository extends CrudRepository<WaselAddress, Long> {

    @Override
    Optional<WaselAddress> findById(Long aLong);

    Optional<WaselAddress> findFirstByIdentificationNumberAndBuildingNumberAndUnitNumberAndZipCode(String id, int buildingNo, int unitNo, int zipCode);


}