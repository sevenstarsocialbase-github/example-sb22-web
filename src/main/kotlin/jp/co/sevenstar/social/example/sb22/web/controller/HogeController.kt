package jp.co.sevenstar.social.example.sb22.web.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HogeController {
  var logger: Logger = LoggerFactory.getLogger(HogeController::class.java)

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
}
