//package jp.co.sevenstar.social.example.sb22.web.kafka
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.kafka.core.KafkaTemplate
//import org.springframework.stereotype.Component
//import org.springframework.util.concurrent.ListenableFutureCallback
//import javax.websocket.SendResult
//
//@Component
//class HelloKafka(var template: KafkaTemplate<String, String>) {
////  @Autowired
////  var template: KafkaTemplate<String, String>? = null
//
//  fun helloKafka() {
//    // コールバックを設定しない非同期呼び出し
//    template.send("sample", "value")
//
//    // コールバックを設定する非同期呼び出し
//    template.send("sample", "value").addCallback(object : ListenableFutureCallback<SendResult<String?, String?>?> {
//      override fun onSuccess(result: SendResult<String?, String?>?) {
//        // 成功時の処理
//      }
//
//      override fun onFailure(ex: Throwable) {
//        // 失敗時の処理
//      }
//    })
//
//    // 同期呼び出し
//    template.send("sample", "value").get()
//  }
//}