package com.soprasteria.devopsassesmenttool.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.devopsassesmenttool.model.Measure;
import com.soprasteria.devopsassesmenttool.sevice.MeasureService;

@RestController
@RequestMapping("/devops")
public class MeasureController {

	private MeasureService measureService;

	public MeasureController(MeasureService measureService) {
		this.measureService = measureService;
	}

	@RequestMapping(value = "measures", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Measure> getAllMeasures() {
		return measureService.findAll();
	}

	@RequestMapping(value = "accounts/{accountId}/measure", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Measure> createMeasure(@PathVariable int accountId, @RequestBody Measure measure)
			throws URISyntaxException {
		try {
			Measure result = measureService.save(measure, accountId);
			return ResponseEntity.created(new URI("/devops/measure/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Measure>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "measure", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Measure> updateMeasure(@RequestBody Measure measure) throws URISyntaxException {
		if (measure.getId() == null) {
			return new ResponseEntity<Measure>(HttpStatus.NOT_FOUND);
		}

		try {

			Measure result = measureService.update(measure);

			return ResponseEntity.created(new URI("/devops/measure/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Measure>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/measure/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteMeasure(@PathVariable Integer id) {
		measureService.delete(id);

		return ResponseEntity.ok().build();
	}

}
