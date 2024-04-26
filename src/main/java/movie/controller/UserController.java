package movie.controller;

import movie.dto.response.TokenResponse;
import movie.dto.request.LoginRequest;
import movie.entity.User;
import movie.service.UserService;
import movie.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

        @PostMapping("register")
//    @RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("Đã gửi email xác nhận đăng ký. Vui lòng kiểm tra hộp thư của bạn.");
    }

    @GetMapping("confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("code") String confirmCode) {
        userService.confirmRegistration(confirmCode);
        return ResponseEntity.ok("Đăng ký tài khoản thành công!");
    }

    @PostMapping("login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userServiceImpl.login(loginRequest), HttpStatus.OK);
    }


}
