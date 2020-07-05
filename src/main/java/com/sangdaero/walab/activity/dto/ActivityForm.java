package com.sangdaero.walab.activity.dto;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ActivityForm {

	Long requestId;

	@NotNull
	@Size(min=2, max=255)
	String title;
	
	@Min(1)
	Long interestCategoryId;
	
	List<Long> userId;
	
	Byte delivery;
	
	List<Byte> volunteerStatus;
	
	Long managerId; 
	
	@NotNull
	@Size(min=10)
	String startDate; 
	
	@NotNull
	@Size(min=5)
	String startTime; 
	
	@NotNull
	@Size(min=10)
	String endDate; 
	
	@NotNull
	@Size(min=5)
	String endTime;
	
	String place;
	
	@NotNull
	@Size(min=10)
	String deadlineDate;
	
	@NotNull
	@Size(min=5)
	String deadlineTime; 
	
	String content;
	
	List<Long> volunteerId;
    
	@JsonIgnore
	MultipartFile files;
    
	String file;
	
}
