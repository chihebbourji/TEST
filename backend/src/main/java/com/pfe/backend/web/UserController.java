package com.pfe.backend.web;

import com.pfe.backend.services.UserService;
import com.pfe.backend.services.model.DeleteUserDto;
import com.pfe.backend.services.model.MakeUserAdminDto;
import com.pfe.backend.services.model.UserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-resources")
@Tag(name = "User Resource")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN_ROLE')||hasAuthority('USER_ROLE')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @GetMapping("/findbyemail/{email}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable final String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }


    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @PostMapping ("/delete")
    public ResponseEntity<Void> deleteById(@RequestBody final DeleteUserDto deleteUserDto){
        userService.deleteById(deleteUserDto.getId());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @PutMapping("/make-user-admin")
    public ResponseEntity<Void> makeUserAdmin(@RequestBody final MakeUserAdminDto makeUserAdminDto){
        userService.makeUserAdmin(makeUserAdminDto);
        return ResponseEntity.noContent().build();
    }
}
