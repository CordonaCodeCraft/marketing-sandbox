package tech.cordona.marketingsandbox.controller

import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tech.cordona.marketingsandbox.dto.FilterParameters
import tech.cordona.marketingsandbox.dto.PageParameters
import tech.cordona.marketingsandbox.dto.ReviewRequest
import tech.cordona.marketingsandbox.dto.Operator
import tech.cordona.marketingsandbox.dto.SortParameters
import tech.cordona.marketingsandbox.service.ReviewService


@RestController
@RequestMapping("api/v1/reviews")
class ReviewController(private val service: ReviewService) {

//	@GetMapping("get-all")
//	fun getAllReviewsPaginated(
//		@RequestParam(required = false, defaultValue = "10") limit: Int,
//		@RequestParam(required = false, defaultValue = "0") pageNumber: Int,
//		@RequestParam(required = false, defaultValue = "content.score") orderBy: String,
//		@RequestParam(required = false, defaultValue = "1.0") score: Double,
//		@RequestParam(required = false, defaultValue = "10") threshold: Long,
//		@RequestParam(required = false, defaultValue = "DESC") sortDirection: Sort.Direction,
//		@RequestParam(required = false, defaultValue = "GREATER_THAN") operator: String
//	): Page<Review> {
//		val request = ReviewRequest(
//			pageParameters = PageParameters(limit = limit, pageNumber = pageNumber),
//			sortParameters = SortParameters(sortDirection = sortDirection, orderBy = orderBy),
//			filterParameters = FilterParameters(
//				operator = ScoreOperator.valueOf(operator),
//				score = score,
//				threshold = threshold
//			)
//		)
//
//		return service.getPaginatedResults(request)
//	}

	@GetMapping("get-all")
	fun getAllReviewsPaginatedNew(
		@RequestParam(required = false, defaultValue = "10") limit: Int,
		@RequestParam(required = false, defaultValue = "0") pageNumber: Int,
		@RequestParam(required = false, defaultValue = "DESC") sortDirection: Sort.Direction,
		@RequestParam(required = false, defaultValue = "content.score") orderBy: String,
		@RequestParam(required = false, defaultValue = "GREATER_THAN") operator: String,
		@RequestParam(required = false, defaultValue = "1.0") score: Double,
		@RequestParam(required = false, defaultValue = "3") threshold: Long,
	) = ReviewRequest(
		pageParameters = PageParameters(limit = limit, pageNumber = pageNumber),
		sortParameters = SortParameters(sortDirection = sortDirection, orderBy = orderBy),
		filterParameters = FilterParameters(
			operator = Operator.valueOf(operator),
			score = score,
			threshold = threshold
		)
	).let { service.getPaginatedAndFilteredReviews(it) }
}