package jp.co.sevenstar.social.example.sb22.web.config

import nz.net.ultraq.thymeleaf.LayoutDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ThymeleafLayoutConfig {
  @Bean
  fun layoutDialect() = LayoutDialect()

  //
//  @Bean
//  fun templateEngine(): SpringTemplateEngine {
//    val templateEngine = SpringTemplateEngine()
//    templateEngine.addDialect(layoutDialect())
//    return templateEngine
//  }
}
