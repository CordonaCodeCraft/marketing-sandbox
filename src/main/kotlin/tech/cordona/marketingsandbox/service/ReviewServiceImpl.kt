package tech.cordona.marketingsandbox.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Service
import tech.cordona.marketingsandbox.dto.Operator
import tech.cordona.marketingsandbox.dto.ReviewRequest
import tech.cordona.marketingsandbox.entity.review.Review
import tech.cordona.marketingsandbox.persistance.ReviewRepository
import java.time.LocalDateTime

@Service
class ReviewServiceImpl(
	private val repository: ReviewRepository,
	private val mongoTemplate: MongoTemplate
) : ReviewService {
	override fun save(review: Review): Review = repository.save(review)
	override fun saveAll(reviews: List<Review>): List<Review> = repository.saveAll(reviews)


	override fun getPaginatedAndFilteredReviews(request: ReviewRequest): Page<Review> {
		val sort = Sort.by(request.sortParameters.sortDirection, request.sortParameters.orderBy)
		val page = PageRequest.of(request.pageParameters.pageNumber, request.pageParameters.limit)
		val date = LocalDateTime.now().minusMonths(request.filterParameters.threshold)
		val query = Query()
			.with(page)
			.with(sort)
			.addCriteria(Criteria.where("createdOn").gt(date))
			.addCriteria(setScoreCriteria(request))
		val filteredReviews = mongoTemplate.find(query, Review::class.java)
		return PageableExecutionUtils.getPage(filteredReviews, page) { mongoTemplate.count(query, Review::class.java) }
	}

	private fun setScoreCriteria(request: ReviewRequest) = when (request.filterParameters.operator) {
		Operator.LESS_THAN -> Criteria.where("content.score").lt(request.filterParameters.score)
		Operator.GREATER_THAN -> Criteria.where("content.score").gt(request.filterParameters.score)
		Operator.EQUAL_TO -> Criteria.where("content.score").`is`(request.filterParameters.score)
	}
}