package com.taragani.RestApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taragani.model.Plan;
import com.taragani.service.PlanService;

@RestController
@CrossOrigin
@RequestMapping("/plans")
public class PlanAPI {
	@Autowired
	private PlanService planService;

	@GetMapping
	public ResponseEntity<List<Plan>> listPlansAction() {
		return  new ResponseEntity<>(planService.getAllPlans(), HttpStatus.OK);
		
	}

	@GetMapping("/{pTitle}")
	public ResponseEntity<Plan> getPlanAction(@PathVariable("id") String pTitle) {
		ResponseEntity<Plan> resp = null;
		Plan plan = planService.getPlan(pTitle);
		if (plan == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(plan, HttpStatus.OK);
		return resp;
	}
	
	@GetMapping("/{field}/{srchValue}")
	public ResponseEntity<List<Plan>> getAllPlans (
		@PathVariable("field") String fieldBy,
		@PathVariable("srchValue") String searchValue)
	{
		ResponseEntity<List<Plan>> resp;
			switch(fieldBy){
			case "speed":
				List<Plan> pbs= planService.getPlansBySpeed(Integer.parseInt(searchValue));
				if(pbs!=null){
					resp=new ResponseEntity<>(pbs,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
				
			case "maxUsage":				
				List<Plan> pbu= planService.getPlansByUsage(Integer.parseInt(searchValue));
				if(pbu!=null){
					resp=new ResponseEntity<>(pbu,HttpStatus.OK);}
				else {
					resp=new ResponseEntity<>(HttpStatus.NOT_FOUND);}
				break;
				
			default:
				resp= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				break;	
		}
		
		return resp;
	}
}
