package tech.cordona.marketingsandbox.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tech.cordona.marketingsandbox.entity.review.Review
import tech.cordona.marketingsandbox.service.ReviewService
import java.time.LocalDateTime


@RestController
@RequestMapping("api/v1/reviews")
class ReviewController(private val service: ReviewService) {

	@GetMapping("/get-all")
	fun getAllReviews() = service.getAll()

	@GetMapping("get-all-paginated")
	fun getAllReviewsPaginated(
		@RequestParam(required = false, defaultValue = "100") pageSize: Int,
		@RequestParam(required = false, defaultValue = "0") pageNumber: Int,
		@RequestParam(required = false, defaultValue = "3") monthLimit: Long,
		@RequestParam(required = false, defaultValue = "1.0") score: Double,
		@RequestParam(required = false, defaultValue = "ASC") sortDirection: Sort.Direction,
		@RequestParam(required = false, defaultValue = "ALL") sentiment: String,
		@RequestParam(required = false, defaultValue = "id") orderBy: String
	): Page<Review> {
		val page = PageRequest.of(pageNumber, pageSize, sortDirection, orderBy)

		return service.getAllWithDateThreshold(LocalDateTime.now().minusMonths(monthLimit), score, page)

//		return service.getAll(page)
	}
}