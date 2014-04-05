package com.sfv.service.photo;

import java.util.List;

import com.sfv.entitybean.system.PhotoComment;
import com.sfv.entitybean.system.Photos;
import com.sfv.form.photo.PhotoAlbumsForm;
import com.sfv.form.photo.PhotoForm;

public interface PhotoService {
	public void saveAlbum(PhotoAlbumsForm form);
	
	public List<PhotoAlbumsForm> queryAlbums(String userid);
	
	//有照片相册
	public List<PhotoAlbumsForm> queryAllAlbums(String userid);
	
	public void savePhoto(PhotoForm form);

	public PhotoForm queryPhoto(String id);
	
	public void saveComment(PhotoComment pc);
	
	public List<PhotoComment> queryComment(String imgId);
	
	//更具相册查询照片
	public List<Photos> queryPhotos(Integer albumId);
}
