package tech.cordona.marketingsandbox.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import tech.cordona.marketingsandbox.entity.review.Review
import tech.cordona.marketingsandbox.persistance.ReviewRepository
import java.time.LocalDateTime

@Service
class ReviewServiceImpl(private val repository: ReviewRepository) : ReviewService {
	override fun save(review: Review): Review = repository.save(review)
	override fun saveAll(reviews: List<Review>): List<Review> = repository.saveAll(reviews)
	override fun getAll(): List<Review> = repository.findAll()
	override fun getAll(page: Pageable): Page<Review> = repository.findAll(page)
	override fun getAllWithDateThreshold(monthLimit: LocalDateTime, score: Double,page: Pageable): Page<Review> =
		repository.findAllByCreatedOnAfterAndContentScoreGreaterThan(monthLimit, score, page)
}