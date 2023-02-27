package plus.log.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plus.log.api.dto.UserDto;
import plus.log.api.entity.LogEntity;
import plus.log.api.entity.UserEntity;
import plus.log.api.parameter.LoginRequest;
import plus.log.api.repository.LogRepository;
import plus.log.api.repository.UserRepository;

import java.util.Date;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://kayu.work:3000", "http://kayu.life:3000"})
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogRepository logRepository;


    @PostMapping(value="/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if (user == null || request.getDeviceId()==null || !request.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok().body("Invalid");
        }
        LogEntity logEntity = new LogEntity();
        String token = UUID.randomUUID().toString();
        Date date = new Date();
        logEntity.setLastLogin(date);
        logEntity.setToken(token);
        logEntity.setDeviceId(request.getDeviceId());
        logRepository.save(logEntity);
        user.setLogId(logEntity.getLogId());
        userRepository.save(user);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping(value="/protected")
    public ResponseEntity<Object> getProtectedResource(@RequestHeader("Authorization") String authorization, @RequestHeader("DeviceId") String deviceId) {
        String token;
        if (authorization != null && deviceId != null && authorization.startsWith("plus ")) {
            token = authorization.replaceFirst("plus ", "");
            LogEntity logEntity = logRepository.findByToken(token);
            Date date = new Date();
            long diffInMilliseconds = date.getTime() - logEntity.getLastLogin().getTime();
            int diffInDays = (int) (diffInMilliseconds / (24 * 60 * 60 * 1000));
            if (logEntity.getDeviceId().equals(deviceId) && diffInDays <= 7) {
                logEntity.setLastLogin(date);
                logRepository.save(logEntity);
                UserDto userDto = new UserDto();
                userDto.setUsername(userRepository.findByLogId(logEntity.getLogId()).getUsername());
                return ResponseEntity.ok().body(userDto);
            }
        }
        return ResponseEntity.ok().body("Invalid");
    }

}
