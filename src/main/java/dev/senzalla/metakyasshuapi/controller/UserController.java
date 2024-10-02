package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.authenticate.Login;
import dev.senzalla.metakyasshuapi.model.user.module.*;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserForm userForm) {
        service.save(userForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("confirm")
    public ResponseEntity<Void> validateUser(@RequestParam @Valid @NotBlank String token) {
        service.validateUser(token);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<UserDto> findUser(@RequestHeader("Authorization") String token) {
        UserFilter userFilter = UserFilter.builder().token(token).build();
        UserDto userDto = service.findUserDto(userFilter);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestHeader("Authorization") String token, @RequestBody @Valid UserForm userForm) {
        service.update(userForm, token);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token) {
        service.delete(token);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestHeader("Authorization") String token, @RequestBody @Valid PasswordForm passwordForm) {
        service.updatePassword(passwordForm, token);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updatePhoto(@RequestHeader("Authorization") String token, @RequestParam("photo") MultipartFile photo) {
        service.updatePhoto(token, photo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/recover")
    public ResponseEntity<Void> recoverAccess(@ModelAttribute("RecoverAccess") @Validated RecoverAccess recoverAccess) {
        service.recoverPassword(recoverAccess);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/reset")
    public ResponseEntity<Void> resetPassword(@RequestParam String token, @RequestBody @Valid Login login) {
        service.resetPassword(token, login);
        return ResponseEntity.accepted().build();
    }
}