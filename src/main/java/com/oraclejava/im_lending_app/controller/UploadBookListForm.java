package com.oraclejava.im_lending_app.controller;

import org.springframework.web.multipart.MultipartFile;

public class UploadBookListForm {
	
	private MultipartFile fileupload;

	public MultipartFile getFileupload() {
		return fileupload;
	}

	public void setFileupload(MultipartFile fileupload) {
		this.fileupload = fileupload;
	}
	
	//get, set
	
}
