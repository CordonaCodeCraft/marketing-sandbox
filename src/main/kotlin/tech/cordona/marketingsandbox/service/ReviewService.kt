package tech.cordona.marketingsandbox.service

import org.springframework.data.domain.Page
import tech.cordona.marketingsandbox.dto.ReviewRequest
import tech.cordona.marketingsandbox.entity.review.Review

interface ReviewService {
	fun save(review: Review): Review
	fun saveAll(reviews: List<Review>): List<Review>
	fun getPaginatedAndFilteredReviews(request: ReviewRequest) : Page<Review>
}