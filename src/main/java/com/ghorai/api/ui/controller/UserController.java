package com.ghorai.api.ui.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
	    	
	    	List<SalaryEntity> salaries = salaryRepository.findByEmpNo(empNo);
	    	  String directoryPath = "C:\\Users\\Public\\Documents\\Salary";
	        String filePath = directoryPath + "\\salary_data_" + empNo + ".xlsx";
	        
	        
	        // Generate the Excel file in memory
	      //  excelService.generateExcel(salaries, filePath);

	        try {
	            // Generate the Excel file and save it to the specified path
	        	excelService.generateExcel(salaries, filePath);

	            // Convert the file to byte array to send as response
	            File file = new File(filePath);
	            byte[] fileBytes = java.nio.file.Files.readAllBytes(file.toPath());

	            // Set headers for file download
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	            headers.setContentDispositionFormData("attachment", file.getName());
                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
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
