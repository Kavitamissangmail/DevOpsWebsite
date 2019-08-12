/**
 * 
 */
package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.AccountLabel;
import com.soprasteria.devopsassesmenttool.model.StaticContent;
import com.soprasteria.devopsassesmenttool.repository.StaticContentRepository;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

/**
 * @author dbkumar
 *
 */
@Service
public class StaticContentService {

	@Autowired
	StaticContentRepository staticContentRepository;

	public List<StaticContent> getAllStaticContents() {
		return staticContentRepository.findAll();
	}

	public StaticContent createStaticContent(StaticContent staticContent) {
		return staticContentRepository.save(staticContent);
	}

	public StaticContent getStaticContentById(Long id) {
		if (!staticContentRepository.existsByScId(id)) {
			throw new ResourceNotFoundException("StaticContentById with id " + id + " not found");
		}
		return staticContentRepository.findByScId(id);
	}

	public StaticContent updateStaticContentById(StaticContent staticContentRequest) {
		if (!staticContentRepository.existsByScId(staticContentRequest.getScId())) {
			throw new ResourceNotFoundException(
					"AccountLabel with id " + staticContentRequest.getScId() + " not found");
		}
		StaticContent staticContent = staticContentRepository.findByScId(staticContentRequest.getScId());

		staticContent.setContent(staticContentRequest.getContent());
		staticContent.setPagename(staticContentRequest.getPagename());

		return staticContentRepository.save(staticContent);
	}

	public ResponseEntity<Object> deleteStaticContentById(Long id) {

		if (!staticContentRepository.existsByScId(id)) {
			throw new ResourceNotFoundException("StaticContent with id " + id + " not found");
		}

		staticContentRepository.delete(id);

		return ResponseEntity.ok().build();
	}

}
