package com.sangdaero.walab.request.controller;

import com.sangdaero.walab.request.dto.RequestDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.sangdaero.walab.activity.dto.AppRequest;
import com.sangdaero.walab.request.service.RequestService;
import com.sangdaero.walab.user.application.dto.UserDto;
import com.sangdaero.walab.user.application.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/requestdata")
public class RequestRestController {

	private RequestService mRequestService;
	private UserService mUserService;
	
	public RequestRestController(RequestService requestService, UserService userService) {
		mRequestService = requestService;
		mUserService = userService;
	}
	
	@PostMapping("/setStatus")
	public RequestDto setStatus(@RequestParam("id") Long id, @RequestParam("status") Byte status) {
		RequestDto requestDto = mRequestService.setStatus(id, status);

		return requestDto;
	}
	
	@PostMapping("/register")
	public String register(@RequestBody AppRequest registerForm) {
		UserDto userDto = mUserService.createUser(registerForm.getEmail(), registerForm.getName());
		mRequestService.createRequest(registerForm.getId(), null, userDto, null, registerForm.getType(), null, null, null, null);
		return "success";
	}

	@PostMapping("/newRegister")
	public String newRegister(@RequestBody AppRequest newRegisterForm) {
		UserDto userDto = mUserService.createUser(newRegisterForm.getEmail(), newRegisterForm.getName());
		mRequestService.createRequest(null, newRegisterForm.getId(), userDto, null, (byte) 1, newRegisterForm.getStartTime() , newRegisterForm.getEndTime() ,newRegisterForm.getTitle(), newRegisterForm.getMemo());
		return "success";
	}

	@RequestMapping(path = "/newProduct", method = RequestMethod.POST,
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public String newProduct(@RequestParam("name") String name, @RequestParam("email") String email,
							 @RequestParam("image") MultipartFile image, @RequestParam("id") Long id,
							 @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {
		UserDto userDto = mUserService.createUser(email, name);
		mRequestService.createRequest(null, id, userDto, image, (byte) 1, startTime, endTime, null, null);
		return "success";
	}
	
}
