package com.sfv.service.photo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.sfv.dao.photo.PhotoDao;
import com.sfv.entitybean.system.PhotoComment;
import com.sfv.entitybean.system.Photos;
import com.sfv.form.photo.PhotoAlbumsForm;
import com.sfv.form.photo.PhotoForm;

public class PhotoServiceImpl implements PhotoService {

	public List<PhotoAlbumsForm> queryAlbums(String userid) {
		List<PhotoAlbumsForm> albums = photoDao.queryAlbums(userid);
		return albums;
	}
	
	public List<PhotoAlbumsForm> queryAllAlbums(String userid) {
		List<PhotoAlbumsForm> albums = photoDao.queryAllAlbums(userid);
		return albums;
	}

	public void saveAlbum(PhotoAlbumsForm form) {
		photoDao.saveAlbum(form);
	}
	public void savePhoto(PhotoForm form){
		photoDao.savePhoto(form);
	}

	public PhotoForm queryPhoto(String id) {
		PhotoForm pForm = new PhotoForm();
		Photos photo = photoDao.queryPhoto(id);
		pForm = pForm.copyFiled(photo);
		return pForm;
	}


	public void saveComment(PhotoComment pc) {
		photoDao.saveComment(pc);
		
	}
	
	private PhotoDao photoDao;

	public PhotoDao getPhotoDao() {
		return photoDao;
	}

	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}
	
	static Logger log = Logger.getLogger("PhotoServiceImpl");

	public List<PhotoComment> queryComment(String imgId) {
		return photoDao.queryComment(imgId);
	}
	
	/**
	 * 根据相册查询照片
	 */
	public List<Photos> queryPhotos(Integer albumId) {
		try
		{
			return photoDao.queryPhotoByCondition(" and albumid=" + albumId);
		}
		catch(Exception e)
		{
			log.equals("queryPhotos error! " + e);
		}
		return null;
	}

}
