package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.repository.FlightRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class FlightService(val repository: FlightRepository) {
}
