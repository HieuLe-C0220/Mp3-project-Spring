package com.meo.mp3.controllers;

import com.meo.mp3.models.songs.Song;
import com.meo.mp3.models.users.account.Profile;
import com.meo.mp3.models.users.account.User;
import com.meo.mp3.services.ProfileService;
import com.meo.mp3.services.UserService;
import com.meo.mp3.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private SongService songService;

    @GetMapping("/list")
    public List<User> findAll() {
        return (List<User>) userService.findAll();
    }

    @GetMapping("/{id}/profile")
    public Profile getById(@PathVariable ("id") Long id) {
        User user = userService.findById(id);
        return profileService.findById(user.getProfile().getId());
    }

    @PutMapping("/{id}/edit")
    public User updateProfile(@RequestBody Profile profile, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        user.setProfile(profile);
        return userService.save(user);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getAllSongByUser(@PathVariable Long id){
        List<Song> songList = songService.getSongsByUserId(id);
        return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
    }
    @GetMapping("{id}/detail")
    public User getUserById(@PathVariable("id") Long id){
         return  userService.findById(id);

    }
    @PostMapping("/{id}/password/update")
    public User changePassword(@PathVariable("id") Long id,@RequestBody String newPass){
        User user = userService.findById(id);
        String password = this.userService.encoder(newPass);
        user.setPassword(password);
        return userService.save(user);
    }
}
