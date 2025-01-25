package com.ghorai.api.ui.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghorai.api.ui.entity.EmpEntity;
import com.ghorai.api.ui.entity.SalaryEntity;
import com.ghorai.api.ui.model.request.UserDetailsModelRequest;
import com.ghorai.api.ui.model.response.UserDetailsModelResponse;
import com.ghorai.api.ui.repositry.SalaryRepository;
import com.ghorai.api.ui.service.ExcelService;
import com.ghorai.api.ui.service.UserService;
import com.ghorai.api.ui.shared.dto.empDto;
import com.ghorai.api.ui.shared.dto.userDto;

@RestController
@RequestMapping("users")  // http://localhost:8080/users
public class UserController {
	
	 @Autowired
	 UserService userService;
	 @Autowired
	 SalaryRepository salaryRepository;
	 @Autowired
	 ExcelService excelService;
	 
	 
	 @GetMapping("/download-salaries")
	    public ResponseEntity<byte[]> downloadSalaries(@RequestParam Long empNo) throws IOException {
	    	List<SalaryEntity> salaries= null;
	    	
	    	Map<String, String> errorResponse = new HashMap<>();
	    	errorResponse.put("message", "Employee not found");
	    	errorResponse.put("status", "404");

	    	
	    	if (!userService.fetchEmployeeByID(empNo).isEmpty() ) {
	    		salaries= salaryRepository.findByEmpNo(empNo);
			}
	    	else {
	    		  // Custom message to be returned
	            String message = "File with ID " + empNo + " not found";
	            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.TEXT_PLAIN); // Set content type as plain text
	          

	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .headers(headers)
	                                 .body(messageBytes);
			}
	    	
	    	
	    	String directoryPath = "C:\\Users\\Public\\Documents\\Salary";
	        String filePath = directoryPath + "\\salary_data_" + empNo + ".xlsx";
	        
	        String message = "File Generated Succefull";
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

	        
	        try {
	           excelService.generateExcel(salaries, filePath);
                File file = new File(filePath);
	            byte[] fileBytes = java.nio.file.Files.readAllBytes(file.toPath());
                HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	            headers.setContentDispositionFormData("attachment", file.getName());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .headers(headers)
                        .body(messageBytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	   @RequestMapping(value = "/pow" , method = RequestMethod.GET)
	   public int pow(@RequestParam(value="base") int base1, @RequestParam(value="ext") int ext1){
		    int pow = (int) Math.pow(base1, ext1);
		    return pow;
		}
	   
	   @RequestMapping(value="/sqrt/{num}",method = RequestMethod.GET)
	   public double sqrt(@PathVariable(value="num") int num1){
	       double sqrtnum=Math.sqrt(num1);
	       return sqrtnum;
	   }
	   
	    @GetMapping("/employee")
	    public List<EmpEntity> fetchDepartmentList()
	    {
	       return userService.fetchDepartmentList();
	    }
	    @RequestMapping(value = "/salary/{emp_num}" , method = RequestMethod.GET)
	     public List<SalaryEntity> fetchemploySalary(@PathVariable(value ="emp_num" ) Long emp_num)
	    {
			return userService.fetchSalary(emp_num);
	    }
		 @PostMapping("/gender/{gender}")
	    public EmpEntity singleEmployee(@PathVariable("gender") String Gender)
	    {
	        return userService.fetchEmployee(Gender);
	    }
	    
	    @PostMapping("/userID/{id}")
	    public Optional<EmpEntity> singleEmployee(@PathVariable("id") Long id)
	    {
	        return userService.fetchEmployeeByID(id);
	    }
	    
	    @GetMapping("/fetchYear")
	    public  List<EmpEntity> fetchYear(@RequestParam("year") int year)
	    {
	       return userService.fetchspeceficYearBirthdateEmploye(year);
	    }
	 	@PostMapping
		public UserDetailsModelResponse createUser(@RequestBody UserDetailsModelRequest userdetails)
		{
			
			UserDetailsModelResponse userDetailsModelResponse = new UserDetailsModelResponse();
			userDto ud = new userDto();
			BeanUtils.copyProperties(userdetails, ud);
			userDto createUserDto=userService.createUser(ud);
			BeanUtils.copyProperties(createUserDto, userDetailsModelResponse);
			return userDetailsModelResponse ;
		}
		
		@PutMapping
		 public String updateUser()
	   {  
			return "User update succesfull";
			
	   }
	   @DeleteMapping 
		public String deleteUsers()
		{
			return "delete users suceefull";
		}
}
