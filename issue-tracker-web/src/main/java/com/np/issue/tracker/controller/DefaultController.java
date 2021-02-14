package com.np.issue.tracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@Slf4j
public class DefaultController {

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {

        String remoteUser = request.getRemoteUser();
        Principal userPrincipal = request.getUserPrincipal();

        /*if (request.isUserInRole("ADMIN")) {
            return "redirect:/events/";
        }*/
        return "redirect:/";
    }
}
