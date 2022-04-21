package com.ferbator.dao.daoImpl;

import com.ferbator.dao.entities.OwnershipCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnershipCatDAO extends JpaRepository<OwnershipCat, Long> {
    void deleteAllByCatId(Long catId);
    void deleteAllByOwnerId(Long ownerId);
}
