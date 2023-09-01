package com.capstone.datamate.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capstone.datamate.Entity.UserEntity;
import com.capstone.datamate.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userve;
	
	// create
	@PostMapping(value = "/postUser", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public UserEntity insertUser(@RequestPart UserEntity user, @RequestPart("userImage") MultipartFile userImage) throws IOException {
		return userve.insertUser(user, userImage);
	}
	
	// read
	@GetMapping("/getAllUsers")
	public List<UserEntity> getAllUsers(){
		return userve.getAllUsers();
	}
	
//	@GetMapping("/getByUsername")
//	public UserEntity findByUsername(@RequestParam String username) {
//		return userve.findByUsername(username);
//	}
	
	@GetMapping("/getByUsername")
	public ResponseEntity<Object> findByUsername(@RequestParam String username) {
	    UserEntity user = userve.findByUsername(username);
	    
	    if (user != null) {
	        // Username is already taken
	        return ResponseEntity.status(HttpStatus.OK).body("{\"exists\": true}");
	    } else {
	        // Username is available
	        return ResponseEntity.status(HttpStatus.OK).body("{\"exists\": false}");
	    }
	}

	
	@GetMapping("/getUserById/{userId}")
	public Optional<UserEntity> findByUserId(@PathVariable int userId){
		return userve.findByUserId(userId);
	}
	
	// update
	@PutMapping("/putUser")
	public UserEntity putUser(@RequestParam int userId, @RequestBody UserEntity newUserDetails) throws Exception{
		return userve.putUser(userId, newUserDetails);
	}
	
	// delete
	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable int userId) {
		return userve.deleteUser(userId);
	}

}
