package tech.cordona.marketingsandbox.dto

import org.springframework.data.domain.Sort

data class ReviewRequest(
	val pageParameters: PageParameters,
	val sortParameters: SortParameters,
	val filterParameters: FilterParameters
)

data class PageParameters(val limit: Int, val pageNumber: Int)
data class SortParameters(val sortDirection: Sort.Direction, val orderBy: String)
data class FilterParameters(val operator: Operator, val score: Double, val threshold: Long)
enum class Operator {
	GREATER_THAN,
	LESS_THAN,
	EQUAL_TO
}