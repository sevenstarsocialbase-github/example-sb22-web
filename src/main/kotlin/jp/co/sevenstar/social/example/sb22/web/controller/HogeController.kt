package jp.co.sevenstar.social.example.sb22.web.controller

import jp.co.sevenstar.social.example.sb22.web.kafka.MessageProducer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HogeController(
  val messageProducer: MessageProducer
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
    GlobalScope.launch {
      runCatching {
        delay(1000L)
        logger.info("world")
        messageProducer.publish()
      }.onFailure {
        logger.error("catch error", it)
      }
    }
    logger.info("hello,")
    Thread.sleep(2000L)
    return "hello"
  }
}
