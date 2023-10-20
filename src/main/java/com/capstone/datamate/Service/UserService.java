package com.capstone.datamate.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capstone.datamate.Entity.UserEntity;
import com.capstone.datamate.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository urepo;
	
	// create user
//	public UserEntity insertUser(UserEntity user, MultipartFile userImage) throws IOException{
//		user.setUserImage(userImage.getBytes());
//		return urepo.save(user);
//	}
	
	public UserEntity insertUser(UserEntity user, MultipartFile userImage) throws IOException {
	    if (userImage != null) {
	        user.setUserImage(userImage.getBytes());
	    }
	    return urepo.save(user);
	}
	
	// read
	public List<UserEntity> getAllUsers(){
		return urepo.findAll();
	}
	
	public UserEntity findByUsername(String username) {
		if(urepo.findByUsername(username) != null)
			return urepo.findByUsername(username);
		else
			return null;
	}
	
	public Optional<UserEntity> findByUserId(int userId){
		if(urepo.findById(userId) != null)
			return urepo.findById(userId);
		else
			return null;
	}
	
	// update user details
	public UserEntity putUser(int userId, UserEntity newUserDetails) throws Exception{
		
		UserEntity user = new UserEntity();
		
		try {
			user = urepo.findById(userId).get();
			
			user.setFirstName(newUserDetails.getFirstName());
			user.setLastName(newUserDetails.getLastName());
			user.setAddress(newUserDetails.getAddress());
			user.setEmail(newUserDetails.getEmail());
			user.setUsername(newUserDetails.getUsername());
			user.setPassword(newUserDetails.getPassword());
			user.setBusinessName(newUserDetails.getBusinessName());
			user.setBusinessType(newUserDetails.getBusinessType());
//			user.setImage(image.getBytes());
			
			return urepo.save(user);
		}catch(NoSuchElementException ex) {
			throw new Exception("ID Number " + userId + " does not exist!");
		}
	}


	// delete user account
	public String deleteUser(int userId) {
		String msg;
		if(urepo.findById(userId).orElse(null) != null) {
			urepo.deleteById(userId);
			
			msg = "Account ID Number " +  userId + " is successfully deleted!";
		}
		else
			msg = "Account ID Number " +  userId + " is NOT found!";
		
		return msg;
	}


}
