package com.cidic.design.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.FileInfoDao;
import com.cidic.design.model.FileInfo;
import com.cidic.design.service.FileInfoService;

@Service
@Component
@Qualifier(value = "fileInfoServiceImpl")
@Transactional
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	@Qualifier(value = "fileInfoDaoImpl")
	private FileInfoDao fileInfoDaoImpl;
	
	@Override
	public void createFile(FileInfo file) {
		fileInfoDaoImpl.createFile(file);
	}

	@Override
	public void deleteFile(int id) {
		fileInfoDaoImpl.deleteFile(id);
	}

	@Override
	public void updateFile(FileInfo file) {
		fileInfoDaoImpl.updateFile(file);
	}

	@Override
	public List<FileInfo> getAllFile() {
		
		return fileInfoDaoImpl.getAllFile();
	}

	@Override
	public List<FileInfo> getFileByType(int type) {
		
		return fileInfoDaoImpl.getFileByType(type);
	}

}
