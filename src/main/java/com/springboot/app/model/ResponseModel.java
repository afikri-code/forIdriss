package com.springboot.app.model;

import com.springboot.app.utils.Constants;
import lombok.Data;

@Data
public class ResponseModel {
	private String status = Constants. FAIL;
	private Object data;
}