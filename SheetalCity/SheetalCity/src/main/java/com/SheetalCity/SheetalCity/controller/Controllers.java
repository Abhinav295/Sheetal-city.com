package com.SheetalCity.SheetalCity.controller;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SheetalCity.SheetalCity.dto.AdvancePaymentDTO;
import com.SheetalCity.SheetalCity.dto.HouseMappingDTO;
import com.SheetalCity.SheetalCity.entity.CityDetails;
import com.SheetalCity.SheetalCity.entity.HouseMapping;
import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.entity.UserLogin;
import com.SheetalCity.SheetalCity.services.BillGenerationService;
import com.SheetalCity.SheetalCity.services.CityDataService;
import com.SheetalCity.SheetalCity.services.HouseMappingService;
import com.SheetalCity.SheetalCity.services.UserDataService;
import com.SheetalCity.SheetalCity.services.UserLoginService;

@RestController
@RequestMapping("sheetal.city")
@CrossOrigin(origins = "http://localhost:3000")
public class Controllers {

	@Autowired
	UserDataService user;
	
	@Autowired
	UserLoginService loginService;
	
	@Autowired
	CityDataService cityDataService;
	
	@Autowired
	HouseMappingService houseMappingService;
	
	@Autowired
	BillGenerationService billGenerationService;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserData userData) {
		String username = user.userDataInsert(userData);
		System.out.println("-------------------Inside the insert block----------------------");
		if(!username.isEmpty()) {
			System.out.println(username);
			return ResponseEntity.status(HttpStatus.OK).body(username);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Occured");
		}
	}
	
	@GetMapping("/getUser/{username}")
	public List<UserData> getUser(@PathVariable("username") String username) {
		return user.getUserData(username);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		boolean isDeleted = user.userDataDelete(id);
		System.out.println("-------------------Inside the Delete Block----------------------");
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inavlid User Name");
		}
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserData>> getAllUsers(){
		List<UserData>userD = user.userDataShow();
		System.out.println("-------------------Inside the Health check----------------------");
		return ResponseEntity.status(HttpStatus.OK).body(userD);
	}
	
	@PutMapping("/updateUser/{id}")
	public List<UserData> updateUser(@PathVariable("id") String id,@RequestBody UserData userData) {
		System.out.println("-------------------Inside the update the block----------------------");
		return user.userDataUpdate(Integer.parseInt(id), userData);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLogin user) {
		String username = loginService.authenticate(user);
		if(!username.isEmpty()) {
			System.out.println(username);
			return ResponseEntity.status(HttpStatus.OK).body(username);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inavlid User Name and Password");
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody UserLogin user) {
		try {
		String username = loginService.AddUserDetails(user);
		if(!username.isEmpty()) {
			System.out.println(username);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(username);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Entry Present");
		}
		}catch(SQLIntegrityConstraintViolationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Entry Present");
		}catch(SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Entry Present");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Entry Present");
		}
	}
	
	@PostMapping("/city/addCity")
	public ResponseEntity<String> addCity(@RequestBody CityDetails city) {
		String cityName = cityDataService.addNewCity(city);
		if(!cityName.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(cityName);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City Is Already Present");
		}
	}
	
	@GetMapping("/city/getAllCities")
	public ResponseEntity<List<CityDetails>> getAllCities(){
		List<CityDetails>cityDetails = cityDataService.getCitiesDetails();
		System.out.println("-------------------Inside the Health check----------------------");
		return ResponseEntity.status(HttpStatus.OK).body(cityDetails);
	}
	
	@PutMapping("city/updateCity")
	public ResponseEntity<CityDetails> updateCity(@RequestBody CityDetails city){
		city = cityDataService.UpdateCityDetails(city);
		return ResponseEntity.status(HttpStatus.OK).body(city);
	}
	
	@PostMapping("house/RegisterHouse")
	public ResponseEntity<Integer> registerHouse(@RequestBody HouseMappingDTO HM){
		int response = houseMappingService.registerHouse(HM);
		if(response == 0){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(HM.getHouseNo());
	}
	
	@GetMapping("house/getAllRegisterdHouse")
	public ResponseEntity<List<HouseMapping>> getAllRegisterdHouse(){
		List<HouseMapping> allRegisterdHouse = houseMappingService.getAllHouseRegisteration();
		return ResponseEntity.status(HttpStatus.OK).body(allRegisterdHouse);
	}
	
	@GetMapping("house/getRegisterdHouse/{id}")
	public ResponseEntity<List<HouseMapping>> getRegisterdHouse(@PathVariable("id") int id){
		List<HouseMapping> allRegisterdHouse = houseMappingService.getHouseRegisteration(id);
		return ResponseEntity.status(HttpStatus.OK).body(allRegisterdHouse);
	}
	
	@GetMapping("/getAllCred")
	public List<UserLogin> getAllCred(){
		return loginService.getAllLoginCred();
	}
	
	@PostMapping("/pay/advancePayment")
	public int advancePayment(@RequestBody AdvancePaymentDTO advanePayment) {
		billGenerationService.generatePaymentBill(advanePayment);
		return 9;
	}
	
}
