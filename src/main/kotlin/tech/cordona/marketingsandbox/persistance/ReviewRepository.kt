package tech.cordona.marketingsandbox.persistance

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import tech.cordona.marketingsandbox.entity.review.Review
import java.time.LocalDateTime

interface ReviewRepository : MongoRepository<Review, String> {
	fun findAllByCreatedOnAfterAndContentScoreGreaterThan(monthLimit: LocalDateTime, score: Double, page: Pageable) : Page<Review>
}