package io.github.tuyendev.passport.controller.web;

import com.naharoo.commons.mapstruct.MappingFacade;
import io.github.tuyendev.passport.constants.App;
import io.github.tuyendev.passport.dto.signup.SignupRequestDto;
import io.github.tuyendev.passport.dto.user.UserDto;
import io.github.tuyendev.passport.extras.Translator;
import io.github.tuyendev.passport.service.user.UserService;
import io.github.tuyendev.passport.utils.PasswordUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

@Controller
public class DefaultPageController {

    private final MappingFacade mapper;

    private final UserService userService;

    public DefaultPageController(MappingFacade mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    private static String validateSignupForm(SignupRequestDto request) {
        if (!Objects.equals(request.getPassword(), request.getRepassword())) {
            return Translator.val("app.signup.views.message.passwordnotmatch");
        }
        if (!PasswordUtil.isPasswordMatched(request.getPassword(), App.PASSWORD_REGEX)) {
            return Translator.val("app.signup.views.message.passwordrequired");
        }
        return "";
    }

    @GetMapping(value = {"/", "/home", "/dashboard", "/index"})
    @PreAuthorize("hasAuthority('A')")
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "views/access/signin";
    }

    @GetMapping("/register")
    public String registerView(Model model, @ModelAttribute("data") SignupRequestDto request, @ModelAttribute("message") String message) {
        model.addAttribute("message", message);
        model.addAttribute("request", request);
        return "views/access/signup";
    }

    @PostMapping("/register")
    public RedirectView submitRegister(@ModelAttribute SignupRequestDto request, RedirectAttributes redirectAttributes) {
        RedirectView view = new RedirectView();
        String validationMessage = validateSignupForm(request);
        if (ObjectUtils.isNotEmpty(validationMessage)) {
            request.setPassword(null);
            request.setRepassword(null);
            redirectAttributes.addAttribute("error", true);
            redirectAttributes.addFlashAttribute("data", request);
            redirectAttributes.addFlashAttribute("message", validationMessage);
            view.setContextRelative(true);
            view.setUrl("/register");
            return view;
        }
        redirectAttributes.addAttribute("register", true);
        userService.create(mapper.map(request, UserDto.class));
        view.setUrl("/login");
        return view;
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "views/access/forgot-password";
    }
}
