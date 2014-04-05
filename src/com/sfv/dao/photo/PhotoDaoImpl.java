package com.sfv.dao.photo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sfv.entitybean.system.PhotoAlbums;
import com.sfv.entitybean.system.PhotoComment;
import com.sfv.entitybean.system.Photos;
import com.sfv.form.photo.PhotoAlbumsForm;
import com.sfv.form.photo.PhotoForm;

public class PhotoDaoImpl extends HibernateDaoSupport implements PhotoDao {

	public void saveAlbum(PhotoAlbumsForm form) {
		
		try{
			PhotoAlbums pojo = new PhotoAlbums();
			pojo.setCreateTime(form.getCreateTime());
			pojo.setName(form.getName());
			pojo.setUserid(form.getUserid());
			pojo.setDescription(form.getDescription());
			this.getHibernateTemplate().saveOrUpdate(pojo);
		}catch(DataAccessException e){
			log.error("PhotoDaoImpl::saveAlbum:", e);
			throw new RuntimeException(e);
		}
	}

	public List<PhotoAlbumsForm> queryAlbums(String userid) {
		List<PhotoAlbums> pojos = this.getHibernateTemplate().find("from PhotoAlbums where userid=?" +
				" and id in(select albumid from Photos)" , userid);
		List<PhotoAlbumsForm> albums = new ArrayList<PhotoAlbumsForm>();
		if(!pojos.equals(null))
		{
			for(int i=0; i<pojos.size(); i++)
			{
				PhotoAlbums pojo = (PhotoAlbums)pojos.get(i);
				PhotoAlbumsForm albumForm = new PhotoAlbumsForm(pojo.getId(), pojo.getName(), pojo.getCreateTime(),
						pojo.getUserid(), pojo.getDescription());
				albums.add(albumForm);
			}
		}
		return albums;
	}
	
	public List<PhotoAlbumsForm> queryAllAlbums(String userid) {
		List<PhotoAlbums> pojos = this.getHibernateTemplate().find("from PhotoAlbums where userid=?" , userid);
		List<PhotoAlbumsForm> albums = new ArrayList<PhotoAlbumsForm>();
		if(!pojos.equals(null))
		{
			for(int i=0; i<pojos.size(); i++)
			{
				PhotoAlbums pojo = (PhotoAlbums)pojos.get(i);
				PhotoAlbumsForm albumForm = new PhotoAlbumsForm(pojo.getId(), pojo.getName(), pojo.getCreateTime(),
						pojo.getUserid(), pojo.getDescription());
				albums.add(albumForm);
			}
		}
		return albums;
	}
	


	public void savePhoto(PhotoForm form) {
		try{
			Photos photo = new Photos();
			photo.setAlbumid(form.getAlbumid());
			photo.setCreateTime(form.getCreateTime());
			photo.setDescription(form.getDescription());
			photo.setImgUrl(form.getImgUrl());
			photo.setImgUrlS(form.getImgUrlS());
			photo.setExtendName(form.getExtendName());
			photo.setName(form.getName());
			photo.setUserid(form.getUserid());
			this.getHibernateTemplate().saveOrUpdate(photo);
		}catch(DataAccessException e){
			log.error("PhotoDaoImpl::savePhoto:", e);
			throw new RuntimeException(e);
		}
	}
	
	static Logger log = Logger.getLogger("PhotoDaoImpl");

	public List<Photos> queryPhotoByCondition(String con) {
		List<Photos> photos = null;
		try{
			return this.getHibernateTemplate().find("from Photos where 1=1 " + con);
		}catch(DataAccessException e){
			log.error("PhotoDaoImpl::queryPhotoByCondition:", e);
		}
		return null;
	}

	public Photos queryPhoto(String id) {
		return (Photos) this.getHibernateTemplate().get(Photos.class, id);
	}

	public void saveComment(PhotoComment pc) {
		try{
			this.getHibernateTemplate().save(pc);
		}catch(DataAccessException e){
			log.error("PhotoDaoImpl::saveComment:", e);
			throw new RuntimeException(e);
		}
		
	}

	public List<PhotoComment> queryComment(String imgId) {
		List<PhotoComment> pcs = new ArrayList<PhotoComment>();
		try{
			pcs = this.getHibernateTemplate().find("from PhotoComment where photoid=?", imgId);
			return pcs;
		}catch(DataAccessException e){
			log.error("PhotoDaoImpl::queryComment:", e);
			throw new RuntimeException(e);
		}
	}
}
