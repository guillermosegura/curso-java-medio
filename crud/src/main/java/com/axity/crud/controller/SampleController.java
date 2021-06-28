package com.axity.crud.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.axity.crud.to.CorreoTO;
import com.axity.crud.to.LogBackTO;
import com.axity.crud.to.ResponseCorreoTO;
import com.axity.crud.to.StatusOperationTO;
import com.axity.crud.to.XmlUtil;

@RestController
@RequestMapping("/")
public class SampleController {

	@PostMapping(value = "/sample", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody String data) {
		System.out.println(data);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@PostMapping(value = "/process", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> process(@RequestBody String data) {

		String[] parts = data.split("=");
		System.out.println(parts[0]);

		String response;
		try {
			String xml = URLDecoder.decode(parts[1], StandardCharsets.UTF_8.toString());
			xml = StringUtils.replace(xml, "\\r", "");
			xml = StringUtils.replace(xml, "\\n", "");
			String msisdn = XmlUtil.getText(xml, "MSISDN");
			if (msisdn != null) {
				msisdn = StringUtils.replace(msisdn, "+52", "");
			}
			System.out.println("Enviar sms a " + msisdn);

			response = "OK";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			response = "NOK";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/actualiza/correo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseCorreoTO> updateEmail(@RequestBody CorreoTO correo) {
		ResponseCorreoTO response = new ResponseCorreoTO();
		LogBackTO logBack = createLogBack("actualiza_correo", "clientescrm");
		response.setLogBack(logBack);
		StatusOperationTO status = new StatusOperationTO();
		status.setCode(0);
		status.setDescription("Procesado correctamente");
		response.setStatusOperation(status);
		response.setCorreo(correo);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private LogBackTO createLogBack(String operationName, String serviceName) {
		LogBackTO logBack = new LogBackTO();
		logBack.setOperationName(operationName);
		logBack.setServiceName(serviceName);
		logBack.setTxId(UUID.randomUUID().toString());
		HttpServletRequest request = getRequest();

		logBack.setRemoteAddr(request.getRemoteAddr());
		if (!StringUtils.isEmpty(request.getHeader("X-Forwarded-For"))) {
			logBack.setForwardedFor(request.getHeader("X-Forwarded-For"));
		}
		return logBack;
	}

	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

}
