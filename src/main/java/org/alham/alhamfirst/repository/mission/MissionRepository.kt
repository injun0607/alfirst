package org.alham.alhamfirst.repository.mission

import org.alham.alhamfirst.dto.mission.MissionDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MissionRepository:JpaRepository<MissionRepository, Long> {


}