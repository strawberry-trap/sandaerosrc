package com.sangdaero.walab.activity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sangdaero.walab.activity.dto.ActivityUserDto;
import com.sangdaero.walab.activity.service.ActivityService;
import com.sangdaero.walab.user.application.dto.UserDetailDto;
import com.sangdaero.walab.user.application.service.UserService;

@RestController
@RequestMapping("/activitydata")
public class ActivityRestController {
	
	private ActivityService mActivityService;
	private UserService mUserService;
	
	public ActivityRestController(ActivityService activityService, UserService userService) {
		mActivityService = activityService;
		mUserService = userService;
	}
	
	@PostMapping("/setTitle")
	public String setTitle(@RequestParam("id") Long id, @RequestParam("title") String title) {
		mActivityService.setTitle(id, title);
		return title;
	}
	
	@PostMapping("/setCategory")
	public String setCategory(@RequestParam("id") Long id, @RequestParam("category") Long interestCategoryId) {
		String interestCategoryName = mActivityService.setInterestCategory(id, interestCategoryId);
		return interestCategoryName;
	}
	
	@PostMapping("/setStatus")
	public Byte setStatus(@RequestParam("id") Long id, @RequestParam("status") Byte status) {
		mActivityService.setStatus(id, status);
		return status;
	}
	
	@GetMapping("/getUsers")
	public List<UserDetailDto> getUsers(@RequestParam("keyword") String keyword) {
		List<UserDetailDto> userList = mUserService.findUsers(keyword);
		return userList;
	}
	
	@PostMapping("/setUsers")
	public List<ActivityUserDto> setUsers(@RequestParam("id") Long id, @RequestParam(value="users[]", required=false) List<Long> userIdList) {
		List<ActivityUserDto> userList = mActivityService.setUsers(id, userIdList, (byte) 0);
		return userList;
	}
	
	@PostMapping("/setVolunteers")
	public List<ActivityUserDto> setVolunteers(@RequestParam("id") Long id, @RequestParam(value="volunteers[]", required=false) List<Long> volunteerIdList) {
		List<ActivityUserDto> userList = mActivityService.setUsers(id, volunteerIdList, (byte) 1);
		return userList;
	}
	
	@PostMapping("/setDelivery")
	public Byte setDelivery(@RequestParam("id") Long id, @RequestParam("delivery") Byte delivery) {
		mActivityService.setDelivery(id, delivery);
		return delivery;
	}
	
	/*@PostMapping("/setPhoneAgree")
	public String setPhoneAgree(@RequestParam("id") Long id, @RequestParam(value="phoneAgree", required=false) String phoneAgree) {
		mActivityService.setPhoneAgree(id, phoneAgree);
		return phoneAgree;
	}*/
	
	@PostMapping("/setVolunteerTime")
	public String setVolunteerTime(@RequestParam("id") Long id, 
			@RequestParam("startDate") String startDate, @RequestParam("startTime") String startTime,
			@RequestParam("endDate") String endDate, @RequestParam("endTime") String endTime) {
		mActivityService.setVolunteerTime(id, startDate, startTime, endDate, endTime);
		return startDate + "." + startTime + "." + endDate + "." + endTime;
	}
	
	@PostMapping("/setPlace")
	public String setPlace(@RequestParam("id") Long id, @RequestParam("place") String place) {
		mActivityService.setPlace(id, place);
		return place;
	}
	
	@PostMapping("/setManager")
	public String setManager(@RequestParam("id") Long id, @RequestParam("manager") Long managerId) {
		String managerName = mActivityService.setManager(id, managerId);
		return managerName;
	}
	
	@PostMapping("/setContent")
	public String setContent(@RequestParam("id") Long id, @RequestParam("content") String content) {
		mActivityService.setContent(id, content);
		return content;
	}
	
	@PostMapping("/setDeadline")
	public String setDeadline(@RequestParam("id") Long id, 
			@RequestParam(value="deadlineDate") String deadlineDate, @RequestParam(value="deadlineTime") String deadlineTime) {
		mActivityService.setDeadline(id, deadlineDate, deadlineTime);
		return deadlineDate + "." + deadlineTime;
	}
}
