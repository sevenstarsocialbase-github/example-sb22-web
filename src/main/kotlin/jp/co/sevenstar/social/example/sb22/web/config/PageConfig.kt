package jp.co.sevenstar.social.example.sb22.web.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "settings")
class PageConfig {
  val title: String = ""
  val name: String = ""
}
