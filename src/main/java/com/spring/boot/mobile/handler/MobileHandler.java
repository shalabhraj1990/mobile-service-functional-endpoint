package com.spring.boot.mobile.handler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.spring.boot.mobile.modal.Mobile;
import com.spring.boot.mobile.modal.Student;
import com.spring.boot.mobile.service.MobileService;

@Component
public class MobileHandler {
	@Autowired
	MobileService mobileService;

	public ServerResponse sayHello(ServerRequest resquest) {
		return ServerResponse.ok().body("welcome to functional programming");

	}

	public ServerResponse studentXmlResponse(ServerRequest resquest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_XML)
				.body(Student.builder().id(1).name("shalabh raj").build());
	}

	public ServerResponse studentJsonResponse(ServerRequest resquest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(Student.builder().id(1).name("shalabh raj").build());
	}

	public ServerResponse getAllMobiles(ServerRequest resquest) {
		List<Mobile> mobiles = mobileService.getAllMobiles();
		return ServerResponse.ok().body(mobiles);
	}

	public ServerResponse getMobileById(ServerRequest resquest) {
		int id = Integer.valueOf(resquest.pathVariable("mobile-id"));
		Mobile mobile = mobileService.getMobileById(id);
		return ServerResponse.ok().body(mobile);
	}

	public ServerResponse saveMobile(ServerRequest resquest) throws ServletException, IOException, URISyntaxException {

		Mobile mobile = resquest.body(Mobile.class);
		List<Mobile> mobiles = mobileService.saveMobile(mobile);
		URI uri = new URI("http://localhost:8080/mobile/" + mobile.getId());
		return ServerResponse.created(uri).body(mobiles);
	}

	public ServerResponse updateMobile(ServerRequest resquest) {
		return null;
	}

	public ServerResponse deleteMobile(ServerRequest resquest) {
		return null;
	}
}
