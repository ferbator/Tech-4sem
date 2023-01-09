package com.ferbator.shelterapi.dao.daoImpl;

import com.ferbator.shelterapi.dao.entities.OwnershipCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnershipCatRepository extends JpaRepository<OwnershipCat, Long> {
    void deleteAllByCatId(Long catId);
    void deleteAllByOwnerId(Long ownerId);
}
