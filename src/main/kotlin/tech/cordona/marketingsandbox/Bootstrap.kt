package tech.cordona.marketingsandbox

import com.github.javafaker.Faker
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import tech.cordona.marketingsandbox.entity.ClientReference
import tech.cordona.marketingsandbox.entity.RewardReference
import tech.cordona.marketingsandbox.entity.SaleReference
import tech.cordona.marketingsandbox.entity.review.Review
import tech.cordona.marketingsandbox.entity.review.struct.Author
import tech.cordona.marketingsandbox.entity.review.struct.Content
import tech.cordona.marketingsandbox.entity.review.struct.ReviewTarget
import tech.cordona.marketingsandbox.entity.review.struct.TargetType
import tech.cordona.marketingsandbox.service.ReviewService
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

//@Component
class Bootstrap(private val service: ReviewService, private val faker: Faker) : ApplicationRunner {

	override fun run(args: ApplicationArguments?) {

		val reviews = mutableListOf<Review>()

//		for (i in 1..50) {
//			reviews.add(createReview())
//		}
//
//		service.saveAll(reviews)

		val first = createReview().copy(createdOn = LocalDateTime.now().minusMonths(4))
		val second = createReview().copy(createdOn = LocalDateTime.now().minusMonths(5))
		val third = createReview().copy(createdOn = LocalDateTime.now().minusMonths(6))

		service.save(first)
		service.save(second)
		service.save(third)

		println()
	}


	private fun createReview() = Review(
		id = UUID.randomUUID().toString(),
		uuid = UUID.randomUUID().toString(),
		metaData = createTarget(),
		createdOn = LocalDateTime.now(),
		author = createAuthor(),
		content = createContent(),
		reward = RewardReference(UUID.randomUUID().toString()),
		sale = SaleReference(UUID.randomUUID().toString())
	)

	private fun createAuthor() = Author(
		client = ClientReference(UUID.randomUUID().toString()),
		alias = faker.name().firstName()
	)

	private fun createContent() = Content(
		score = getRandomScore(0, 5),
		comment = faker.lorem().characters()
	)

	private fun createTarget() = ReviewTarget(
		type = TargetType.STORE,
		reference = UUID.randomUUID().toString()
	)


	fun getRandomScore(min: Int, max: Int): Double {
		require(min < max) { "Invalid range [$min, $max]" }
		return min + Random.nextDouble() * (max - min)
	}
}