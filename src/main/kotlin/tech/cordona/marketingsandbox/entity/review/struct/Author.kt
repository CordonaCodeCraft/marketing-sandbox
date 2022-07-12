package tech.cordona.marketingsandbox.entity.review.struct

import tech.cordona.marketingsandbox.entity.ClientReference

data class Author(
	val client: ClientReference,
	val alias: String
)
