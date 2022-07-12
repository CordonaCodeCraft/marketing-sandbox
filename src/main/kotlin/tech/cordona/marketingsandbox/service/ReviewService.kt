package tech.cordona.marketingsandbox.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import tech.cordona.marketingsandbox.entity.review.Review
import java.time.LocalDateTime

interface ReviewService {
	fun save(review: Review): Review
	fun saveAll(reviews: List<Review>): List<Review>
	fun getAll(): List<Review>
	fun getAll(page: Pageable): Page<Review>
	fun getAllWithDateThreshold(monthLimit: LocalDateTime, score: Double,page: Pageable): Page<Review>
}