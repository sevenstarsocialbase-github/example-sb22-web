// package com.example.demo.auth
//
// import com.example.demo.config.MyDetail
// import org.springframework.security.authentication.AuthenticationProvider
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
// import org.springframework.security.core.Authentication
// import org.springframework.security.core.AuthenticationException
// import org.springframework.security.core.GrantedAuthority
// import org.springframework.security.core.authority.SimpleGrantedAuthority
// import org.springframework.stereotype.Component
//
// @Component
// class MyAuthenticationProvider : AuthenticationProvider {
//  @Throws(AuthenticationException::class)
//  override fun authenticate(authentication: Authentication): Authentication {
//    val authorities: Collection<GrantedAuthority?> = listOf(
//            SimpleGrantedAuthority("AUTH001"),
//            SimpleGrantedAuthority("AUTH002"),
//            SimpleGrantedAuthority("ADMIN"),
//            SimpleGrantedAuthority("ROLE_ADMIN"))
// //    val token = MyUsernamePasswordAuthenticationToken("kagami", "hoge", authorities)
//    val token = UsernamePasswordAuthenticationToken("hoge", "fuga", authorities)
//    val details = MyDetail()
//    details.hogeValue = "hogeValue"
//    details.hogeList = listOf("aaa", "bbb", "ccc")
//    token.details = details
// //    token.setSampleLocalDateTime(LocalDateTime.now())
//    return token
//  }
//
//  override fun supports(authentication: Class<*>?): Boolean {
//    return true
//  }
// }
