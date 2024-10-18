package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.authenticate.Login;
import dev.senzalla.metakyasshuapi.model.user.module.*;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Register a new user")
    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserForm userForm) {
        service.save(userForm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Validate a user with a token")
    @PatchMapping("confirm")
    public ResponseEntity<Void> validateUser(@RequestParam @Valid @NotBlank String token) {
        service.validateUser(token);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Find a user by token")
    @GetMapping
    public ResponseEntity<UserDto> findUser(@RequestHeader("Authorization") String token) {
        UserFilter userFilter = UserFilter.builder().token(token).build();
        UserDto userDto = service.findUserDto(userFilter);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Update a user")
    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestHeader("Authorization") String token, @RequestBody @Valid UserForm userForm) {
        service.update(userForm, token);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Delete a user by token")
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token) {
        service.delete(token);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a user's password")
    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestHeader("Authorization") String token, @RequestBody @Valid PasswordForm passwordForm) {
        service.updatePassword(passwordForm, token);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a user's photo")
    @PatchMapping(value = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updatePhoto(@RequestHeader("Authorization") String token, @RequestParam("photo") MultipartFile photo) {
        service.updatePhoto(token, photo);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Recover access for a user")
    @PostMapping("/recover")
    public ResponseEntity<Void> recoverAccess(@ModelAttribute("RecoverAccess") @Validated RecoverAccess recoverAccess) {
        service.recoverPassword(recoverAccess);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Reset a user's password")
    @PatchMapping("/reset")
    public ResponseEntity<Void> resetPassword(@RequestParam String token, @RequestBody @Valid Login login) {
        service.resetPassword(token, login);
        return ResponseEntity.accepted().build();
    }
}