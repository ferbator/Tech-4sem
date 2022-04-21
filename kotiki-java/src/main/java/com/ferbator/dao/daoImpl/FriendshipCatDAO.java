package com.ferbator.dao.daoImpl;

import com.ferbator.dao.entities.FriendshipCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipCatDAO extends JpaRepository<FriendshipCat, Long> {
    void deleteAllByFirstCatIdOrSecondCatId(Long firstCatId, Long secondCatId);
}
