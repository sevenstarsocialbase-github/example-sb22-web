package jp.co.sevenstar.social.example.sb22.web.controller

import brave.Tracer
import brave.Tracing
import com.ninjasquad.springmockk.MockkBean
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.kotlintest.spring.SpringListener
import jp.co.sevenstar.social.example.sb22.web.kafka.MessageProducer
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher.matchAll
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(HogeController::class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HogeControllerTest : StringSpec() {
  @Autowired
  lateinit var mockMvc: MockMvc

  @MockkBean
  lateinit var messageProducer: MessageProducer

  @MockkBean
  lateinit var tracer: Tracer

  override fun listeners() = listOf(SpringListener)

  override fun beforeTest(testCase: TestCase) {
    tracer = Tracing.newBuilder().build().tracer()
  }

  init {
    "login" {
      mockMvc.perform(get("/login").accept(MediaType.TEXT_PLAIN))
        .andExpect(
          matchAll(
            status().isOk,
            content().string(containsString("<title>Spring Security Example</title>")),
            content().string(containsString("User Name:")),
            content().string(containsString("Password:")),
            content().string(containsString("Sign In"))
          )
        )
    }
  }
}
