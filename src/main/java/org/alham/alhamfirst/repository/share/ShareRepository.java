package org.alham.alhamfirst.repository.share;

import org.alham.alhamfirst.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long>{

    @Query("SELECT DISTINCT s FROM Share s LEFT JOIN FETCH s.schedule WHERE s.id = :id")
    public Share findShareByIdWithSchedule(Long id);

}
