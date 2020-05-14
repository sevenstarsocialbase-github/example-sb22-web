package jp.co.sevenstar.social.example.sb22.web.kafka

import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.camel.builder.endpoint.EndpointRouteBuilder
import org.springframework.stereotype.Component

@Component
class MessagePublishRoute : EndpointRouteBuilder() {
  override fun configure() {
    from(direct("kafka-producer-route")).routeId("kafka-producer-route")
      .to(kafka("user.example").serializerClass(KafkaAvroSerializer::class.java.canonicalName))
  }
}
