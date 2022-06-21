package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferTypeRepository extends JpaRepository<TransferType, Integer> {

    TransferType findById(int id);

    TransferType findByTransferTypeDesc(String transferTypeDesc);

}
