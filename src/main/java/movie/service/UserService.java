package movie.service;

import movie.entity.ConfirmEmail;
import movie.entity.User;
import movie.repository.ConfirmEmailRepository;
import movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmEmailRepository confirmEmailRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        user.setActive(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        ConfirmEmail confirmEmail = new ConfirmEmail();
        confirmEmail.setUserID(user.getUserID());
        confirmEmail.setUser(savedUser);
        confirmEmail.setConfirmCode(generateRandomCode(10)); // Độ dài mã xác nhận là 10 ký tự
        // Tạo thời điểm hết hạn là 30 phút sau thời điểm hiện tại
        Date expiredTime = new Date(System.currentTimeMillis() + (30 * 60 * 1000));
        confirmEmail.setExpiredTime(expiredTime);
        confirmEmailRepository.save(confirmEmail);

        // Gửi email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Xác nhận đăng ký tài khoản");
        message.setText("Xin chào " + user.getName() + ",\n"
                + "Vui lòng click vào đường link sau để xác nhận đăng ký tài khoản:\n"
                + "http://localhost:8080/authentication/confirm?code=" + confirmEmail.getConfirmCode());
        javaMailSender.send(message);
    }

    public void confirmRegistration(String confirmCode) {
        ConfirmEmail confirmEmail = confirmEmailRepository.findByConfirmCode(confirmCode);
        if (confirmEmail != null && !confirmEmail.isConfirm() && confirmEmail.getExpiredTime().after(new Date())) {
            confirmEmail.getUser().setActive(true);
            confirmEmail.setConfirm(true);
            userRepository.save(confirmEmail.getUser());
            confirmEmailRepository.save(confirmEmail);
        }
    }

    private String generateRandomCode(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}