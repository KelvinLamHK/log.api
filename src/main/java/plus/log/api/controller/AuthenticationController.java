package plus.log.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plus.log.api.dto.UserDto;
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
        user.setLast_login(date);
        user.setToken(token);
        userRepository.save(user);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping(value="/protected")
    public ResponseEntity<Object> getProtectedResource(@RequestHeader("Authorization") String authorization) {
        // Extract the token from the Authorization header
        String token = authorization.replaceFirst("plus ", "");

        if(token!=null){
            UserEntity user = userRepository.findByToken(token);
            if(user!=null){
                Date date = new Date();
                long diffInMilliseconds = date.getTime() - user.getLast_login().getTime();
                int  diffInDays = (int) (diffInMilliseconds/(24*60*60*1000));

                if(diffInDays<=7){
                    return ResponseEntity.ok().body("success");
                }
            }
        }

        return ResponseEntity.ok().body("No");
    }

    @PostMapping(value="/token")
    public ResponseEntity<Object> getUser(@RequestHeader("Authorization") String authorization ){
        String token = authorization.replaceFirst("plus ", "");
        UserEntity user = userRepository.findByToken(token);
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        return ResponseEntity.ok().body(userDto);
    }


}
