package jpabook.jpashop.controller

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@Slf4j
class HomeController{

    val logger = LoggerFactory.getLogger(HomeController::class.java)

    @RequestMapping("/")
    fun home(): String {
        logger.info("home controller")
        return "home"
    }
}