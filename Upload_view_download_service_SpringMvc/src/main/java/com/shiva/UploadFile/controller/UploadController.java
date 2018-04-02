package com.shiva.UploadFile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.xml.sax.SAXException;

import com.shiva.UploadFile.IService.IUploadService;
import com.shiva.UploadFile.model.User;

@Controller
public class UploadController {

	@Autowired
	IUploadService uploadSerivce;

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> saveUser(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		
		String userName=req.getParameter("userName");
		String emailId=req.getParameter("emailId");
		String mobileNumber=req.getParameter("mobileNumber");
		String gender=req.getParameter("gender");
		
		User user= new User();

		user.setName(userName);
		user.setMobileNumber(mobileNumber);
		
		user.setGender(gender);
		user.setEmailId(emailId);
		
		MultipartHttpServletRequest multiPartRequest = new DefaultMultipartHttpServletRequest(req);

		try {

			multiPartRequest = (MultipartHttpServletRequest) req;
			multiPartRequest.getParameterMap();

			Iterator<String> itr = multiPartRequest.getFileNames();
			while (itr.hasNext()) {

				MultipartFile mFile = multiPartRequest.getFile(itr.next());

				String randomAlpahaNumericString = RandomStringUtils.randomAlphanumeric(10);

				String nameOfFile = FilenameUtils.getBaseName(mFile.getOriginalFilename());
				String fileExtension = FilenameUtils.getExtension(mFile.getOriginalFilename());
				String newFileName = nameOfFile + '_' + randomAlpahaNumericString + '.' + fileExtension;
				user.setDoucmentName(newFileName);
				File dest= new File("/home/vekomy/UploadedFiles/"+newFileName);
				mFile.transferTo(dest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
		
		uploadSerivce.saveUser(user);

     return Collections.singletonMap("response", "Success");

	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET,produces = "application/json")
	public @ResponseBody List<User>  getUsers()
	{	
		return uploadSerivce.getAllUsers();

	}

	
	@RequestMapping(value = "/viewFile/{fileName}/", method = RequestMethod.GET,produces = "application/json")
	   public @ResponseBody Map<String, String>  viewResume(@PathVariable("fileName") String fileName,HttpServletRequest req, HttpServletResponse response) throws IOException, SAXException, TikaException {
		
		
		String filePath="/home/vekomy/UploadedFiles/"+File.separator+fileName;
		
		 File file = new File(filePath);
		  Parser parser = new AutoDetectParser();
		  //OpenDocumentParser parser = new OpenDocumentParser();
	      BodyContentHandler handler = new BodyContentHandler();
	      Metadata metadata = new Metadata();
	      FileInputStream inputstream = new FileInputStream(file);
	      ParseContext context = new ParseContext();
	      
	      //parsing the file
	      parser.parse(inputstream, handler, metadata, context);
	     // System.out.println("File content : " + handler.toString());
	
	      return Collections.singletonMap("response", handler.toString());
	
	}
	
	
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.POST,produces = "application/json")
	public @ResponseBody Map<String, String> deleteUser(@PathVariable("id") String id, HttpServletRequest req)
			throws NumberFormatException, Exception {

		uploadSerivce.deleteUser(id);
		return Collections.singletonMap("response", "Success");
	
	}
	
	
}
