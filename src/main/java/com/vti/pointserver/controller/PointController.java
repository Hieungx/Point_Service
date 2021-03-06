package com.vti.pointserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.pointserver.entity.Subject;
import com.vti.pointserver.entity.SubjectPoint;
import com.vti.pointserver.entity.User;
import com.vti.pointserver.service.GroupUserService;
import com.vti.pointserver.service.SubjectPointService;
import com.vti.pointserver.service.SubjectService;
import com.vti.pointserver.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/points")

public class PointController {
	@Autowired
	private GroupUserService groupUserService;
	
	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectPointService subjectPointService;
	
//	danh sach cac diem cua 1 user
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<?> getByID(@PathVariable(name= "id") int id) {	
		return new ResponseEntity<List<SubjectPoint>>(subjectPointService.findByUser(id), HttpStatus.OK);
	}
	
//	//Danh sach diem cua 1 group
//	@GetMapping(value = "/group/{id}")
//	public ResponseEntity<?> getByGroupId(@PathVariable(name= "id") int id) {
//		List<User> users = groupUserService.getUsersOfGroup(id);
//		return new ResponseEntity<List<SubjectPoint>>(subjectPointService.findByGroup(users), HttpStatus.OK);
//	}
	
	//Update diem cua 1 sinh vien
	@PutMapping(name = "/user/{id}")
	  public ResponseEntity<?> updatePoint(@PathVariable(name= "id") int id, @RequestBody SubjectPoint subjectPoint) {
		subjectPoint.setId(id);
		subjectPointService.save(subjectPoint);
	    return new ResponseEntity<SubjectPoint>(subjectPointService.save(subjectPoint), HttpStatus.OK);
	}
	
	//Danh sach hoc sinh gioi
	@GetMapping(value = "/users/gioi")
	public ResponseEntity<?> HSG() {	
		return new ResponseEntity<List<SubjectPoint>>(subjectPointService.gioi(), HttpStatus.OK);
	}
	//Danh sach hoc sinh kha
	@GetMapping(value = "/users/kha")
	public ResponseEntity<?> HSK() {	
		return new ResponseEntity<List<SubjectPoint>>(subjectPointService.kha(), HttpStatus.OK);
	}
	//Danh sach hoc sinh gioi
	@GetMapping(value = "/users/tb")
	public ResponseEntity<?> HSTB() {	
		return new ResponseEntity<List<SubjectPoint>>(subjectPointService.tb(), HttpStatus.OK);
	}
	//Danh sach diem theo ten mon
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getBySubjectId(@PathVariable(name= "id") Integer id) {	
		return new ResponseEntity<List<SubjectPoint>>(subjectPointService.findBySubject(id), HttpStatus.OK);
	}
}
