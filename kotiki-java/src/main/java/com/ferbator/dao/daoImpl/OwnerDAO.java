package com.ferbator.dao.daoImpl;

import com.ferbator.dao.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Long> {
}
