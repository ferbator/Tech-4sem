package com.ferbator.shelterapi.dao.daoImpl;

import com.ferbator.shelterapi.dao.entities.FriendshipCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipCatRepository extends JpaRepository<FriendshipCat, Long> {
    void deleteAllByFirstCatIdOrSecondCatId(Long firstCatId, Long secondCatId);
}
