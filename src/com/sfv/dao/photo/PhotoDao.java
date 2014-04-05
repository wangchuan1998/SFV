package com.sfv.dao.photo;

import java.util.List;
import java.util.Map;

import com.sfv.entitybean.system.PhotoComment;
import com.sfv.entitybean.system.Photos;
import com.sfv.form.photo.PhotoAlbumsForm;
import com.sfv.form.photo.PhotoForm;

public interface PhotoDao {
	public void saveAlbum(PhotoAlbumsForm form);
	
	//有照片的相册
	public List<PhotoAlbumsForm> queryAlbums(String userid);
	
	//所有相册
	public List<PhotoAlbumsForm> queryAllAlbums(String userid);
	
	public void savePhoto(PhotoForm form);
	
	public List<Photos> queryPhotoByCondition(String con);
	
	public Photos queryPhoto(String id);
	
	public void saveComment(PhotoComment pc);
	
	public List<PhotoComment> queryComment(String imgId);
}
