package com.soprasteria.devopsassesmenttool.sevice;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprasteria.devopsassesmenttool.model.Measure;
import com.soprasteria.devopsassesmenttool.repository.AccountRepository;
import com.soprasteria.devopsassesmenttool.repository.MeasureRepository;

@Service
public class MeasureService {

	private MeasureRepository measureRepository;

	private AccountRepository accountRepository;

	@Autowired
	public MeasureService(MeasureRepository measureRepository, AccountRepository accountRepository) {
		this.measureRepository = measureRepository;
		this.accountRepository = accountRepository;
	}

	public Measure save(Measure measure, int accountId) {
		if (measure.getId() != null && measureRepository.exists(measure.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		measure.setAccount(accountRepository.findOne(accountId));

		return measureRepository.save(measure);
	}

	public Measure update(Measure measure) {
		if (measure.getId() != null && !measureRepository.exists(measure.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		measure.setAccount(measureRepository.findOne(measure.getId()).getAccount());

		return measureRepository.save(measure);
	}

	public List<Measure> findAll() {
		return measureRepository.findAll();
	}

	public Measure findOne(Integer id) {
		return measureRepository.findOne(id);
	}

	public void delete(Integer id) {
		measureRepository.delete(id);
	}

}
