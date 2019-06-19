package com.soprasteria.devopsassesmenttool.sevice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.soprasteria.devopsassesmenttool.model.DBFile;
import com.soprasteria.devopsassesmenttool.repository.DBFileRepository;
import com.soprasteria.devopsassesmenttool.util.FileStorageException;
import com.soprasteria.devopsassesmenttool.util.MyFileNotFoundException;

@Service
public class DBFileService {

	@Autowired
	private DBFileRepository dbFileRepository;
	
	public DBFile storeFile(MultipartFile file, Long userId, Long qId, Long answerId) {
		// Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), userId, qId, answerId, file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}
	
	public DBFile getFile(Long fileId) {
        DBFile dbFile = dbFileRepository.findOne(fileId);
        if(dbFile==null)
        	throw new MyFileNotFoundException("File not found with id " + fileId);
        
        return dbFile;
    }
	
	public void deleteFile(Long fileId) {
		DBFile dbFile = dbFileRepository.findOne(fileId);
        if(dbFile==null)
        	throw new MyFileNotFoundException("File not found with id " + fileId);
		dbFileRepository.delete(fileId);
	}
}
