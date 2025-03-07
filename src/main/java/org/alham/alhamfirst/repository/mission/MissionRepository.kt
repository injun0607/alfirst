package org.alham.alhamfirst.repository.mission

import org.alham.alhamfirst.entity.mission.Mission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MissionRepository: JpaRepository<Mission, Long> {


}