package plus.log.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plus.log.api.entity.UserEntity;
import plus.log.api.parameter.LoginRequest;
import plus.log.api.repository.UserRepository;

import java.util.Date;
import java.util.UUID;

@RestController
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping(value="/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if (user == null || !request.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok().body("No");
        }

        String token = UUID.randomUUID().toString();
        Date date = new Date();
        user.setLastLogin(date);
        user.setToken(token);
        userRepository.save(user);

        return ResponseEntity.ok().body(token);
    }

    @GetMapping(value="/protected")
    public ResponseEntity<Object> getProtectedResource(@RequestHeader("Authorization") String authorization) {
        // Extract the token from the Authorization header
        String token = authorization.replaceFirst("Bearer ", "");

        if(token!=null){
            UserEntity user = userRepository.findByToken(token);
            if(user!=null){
                Date date = new Date();
                long diffInMilliseconds = date.getTime() - user.getLastLogin().getTime();
                int  diffInDays = (int) (diffInMilliseconds/(24*60*60*1000));

                if(diffInDays<=7){
                    return ResponseEntity.ok(user.getUsername());
                }
            }
        }

        return ResponseEntity.ok("login");
    }

    @GetMapping(value="/hi")
    public ResponseEntity<Object> hi() {

        return ResponseEntity.ok().body("yesss");
    }

}
