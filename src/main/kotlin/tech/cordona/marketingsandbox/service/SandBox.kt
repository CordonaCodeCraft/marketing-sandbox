package tech.cordona.marketingsandbox.service

import java.time.LocalDateTime


class SandBox {


}

fun main(args: Array<String>) {
    val now = LocalDateTime.now()
    val minus = now.minusMonths(3)
    println(now)
    println(minus)
}