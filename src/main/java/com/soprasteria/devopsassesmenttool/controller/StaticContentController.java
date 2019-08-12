package com.soprasteria.devopsassesmenttool.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.StaticContent;
import com.soprasteria.devopsassesmenttool.sevice.StaticContentService;

@RestController
@RequestMapping("/devops")
public class StaticContentController {

	@Autowired
	StaticContentService staticcontentservice;

	@RequestMapping(value = "/getAllStaticContents", method = RequestMethod.GET)
	public List<StaticContent> getAllStaticContents() {
		return staticcontentservice.getAllStaticContents();
	}

	@RequestMapping(value = "/StaticContent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public StaticContent createStaticContent(@RequestBody StaticContent StaticContent) {
		return staticcontentservice.createStaticContent(StaticContent);
	}

	@RequestMapping(value = "/StaticContent/{id}", method = RequestMethod.GET)
	public StaticContent StaticContentById(@PathVariable(value = "id") Long id) {
		return staticcontentservice.getStaticContentById(id);
	}

	@RequestMapping(value = "/StaticContent", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public StaticContent updateStaticContent(@RequestBody StaticContent StaticContent) {
		return staticcontentservice.updateStaticContentById(StaticContent);
	}

	@Transactional
	@RequestMapping(value = "/StaticContent/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteStaticContentById(@PathVariable(value = "Id") Long Id) {
		return staticcontentservice.deleteStaticContentById(Id);
	}

}
