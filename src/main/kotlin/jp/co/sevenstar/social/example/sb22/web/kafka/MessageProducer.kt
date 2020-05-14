package jp.co.sevenstar.social.example.sb22.web.kafka

import jp.co.sevenstar.social.example.sb22.schema.avro.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.ExchangeBuilder
import org.apache.camel.builder.endpoint.dsl.DirectEndpointBuilderFactory.direct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MessageProducer(val producerTemplate: ProducerTemplate) {
  companion object {
    private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
  }

  suspend fun publish() {
    withContext(Dispatchers.IO) {
      logger.info("start send")
      producerTemplate.send(direct("kafka-producer-route").uri,
        ExchangeBuilder.anExchange(producerTemplate.camelContext)
          .withBody(User().apply {
            setName("sevenstars")
            favoriteNumber = 7
            favoriteColor = "black"
          })
          .build()
      )
      logger.info("end send")
    }
  }
}
