package tech.cordona.marketingsandbox.entity.review

import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.TimeSeries
import org.springframework.data.mongodb.core.timeseries.Granularity.HOURS
import tech.cordona.marketingsandbox.entity.DocumentEntity
import tech.cordona.marketingsandbox.entity.RewardReference
import tech.cordona.marketingsandbox.entity.SaleReference
import tech.cordona.marketingsandbox.entity.review.struct.Author
import tech.cordona.marketingsandbox.entity.review.struct.Content
import tech.cordona.marketingsandbox.entity.review.struct.ReviewTarget
import java.time.LocalDateTime

@TimeSeries(
	collection = "review",
	timeField = "createdOn",
	metaField = "metaData",
	granularity = HOURS
)
@TypeAlias("review")
data class Review(
	override val id: String,
	override val uuid: String,
	val metaData: ReviewTarget,
	val createdOn: LocalDateTime,
	val author: Author,
	val content: Content,
	val reward: RewardReference,
	val sale: SaleReference? = null
) : DocumentEntity