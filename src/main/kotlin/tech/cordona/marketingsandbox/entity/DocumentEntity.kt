package tech.cordona.marketingsandbox.entity


import org.springframework.data.annotation.Id

interface DocumentEntity {
	@get:Id
	val id: String
	val uuid: String
}