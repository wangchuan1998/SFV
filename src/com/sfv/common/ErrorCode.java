package com.sfv.common;

/**
 * 错误码常量类
 * @author dbq
 *
 */
public class ErrorCode {

	/**
	 * 数据库错误类
	 */
	//数据库连接错误
	public static String DATABASE_CONNECTION_FAILED = "001";
	
	//数据库不存在
	public static String DATABASE_NOT_EXIST = "002";
	
	//表不存在
	public static String TABEL_NOT_EXIST = "003";
	
	//字段不存在
	public static String FIELD_NOT_EXIST = "004";
	
	/**
	 * 登录错误类
	 */
	//用户名不存在
	public static String USERNAME_NOT_EXIST = "005";
	
	//用户名与密码不匹配
	public static String PASSWORD_ERROR = "006";
	
	/**
	 * 文件操作
	 */
	//不是文件
	public static String IS_NOT_FILE = "007";
	
	//文件不存在
	public static String FILE_NOT_EXSIT = "008";
	
	//文件删除失败
	public static String FILE_DELETE_FAILED = "009";
}
