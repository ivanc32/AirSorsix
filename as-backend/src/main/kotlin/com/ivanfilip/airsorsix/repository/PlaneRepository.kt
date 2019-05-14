package com.ivanfilip.airsorsix.repository

import com.ivanfilip.airsorsix.domain.Plane
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaneRepository : JpaRepository<Plane, String>{
    fun findPlaneById(Id: String): Plane?
}