package jp.co.sevenstar.social.example.sb22.web.controller

import brave.http.HttpTracing
import jp.co.sevenstar.social.example.sb22.web.kafka.MessageProducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HogeController(
  val messageProducer: MessageProducer,
  val tracing: HttpTracing
) {
  companion object {
    private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
  }

  @RequestMapping("/hoge")
  fun hoge() {
    logger.info("HogeController.hoge() called!!")
  }

  @RequestMapping("/content1")
  fun content1(): String {
    logger.info("HogeController.content1() called!!")
    return "content1"
  }

  @RequestMapping("/content2")
  fun content2(): String {
    logger.info("HogeController.content2() called!!")
    return "content2"
  }

  @PostMapping("/send")
  fun send(): String {
    val traceId = tracing.tracing().tracer().currentSpan().context().traceIdString()

    CoroutineScope(Dispatchers.IO).launch {
      runCatching {
        delay(1000L)
        logger.info("world - traceId=[$traceId]")
        messageProducer.publish()
      }.onFailure {
        logger.error("catch error - traceId=[$traceId]", it)
      }
    }
    logger.info("hello, - traceId=[$traceId]")
    Thread.sleep(2000L)
    return "hello"
  }
}
