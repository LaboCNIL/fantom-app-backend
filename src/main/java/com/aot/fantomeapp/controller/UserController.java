package com.aot.fantomeapp.controller;

import com.aot.fantomeapp.config.auth.ConnectedUser;
import com.aot.fantomeapp.dto.UserDto;
import com.aot.fantomeapp.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

   @GetMapping("/me")
   public ResponseEntity<UserDto> getMe(ConnectedUser connectedUser) {
      return ResponseEntity.ok(UserMapper.connectedUserToDto(connectedUser));
   }
}
