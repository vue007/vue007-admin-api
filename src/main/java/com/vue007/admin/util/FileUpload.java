package com.vue007.admin.util;

public class FileUpload {
	
	private String fileName;													//文件名
	private String filePath;													//文件路径
	private String [] allowType = {"jpg", "jpeg", "bmp" , "png", "gif", "zip","xlsx","apk"};	//允许的文件类型
	private Integer maxSize = 25 * 1024 * 1024;									//文件最大值,默认4M
	private Integer minSize = 1;												//文件最小值,默认1B
	
	/**
	 * 默认构造方法
	 */
	public FileUpload() {
		
	}
	
	/**
	 * 设置参数
	 * @param fileName 文件名
	 * @param filePath 文件路径
	 * @param allowType 允许的文件类型
	 * @param maxSize 文件最大值
	 * @param minSize 文件最小值
	 */
	public FileUpload(String fileName, String filePath, String[] allowType, Integer maxSize, Integer minSize) {
		if(fileName != null){
			this.fileName = fileName;
		}
		if(filePath != null){
			this.filePath = filePath;
		}
		if(allowType != null){
			this.allowType = allowType;
		}
		if(filePath != null){
			this.maxSize = maxSize;
		}
		if(minSize != null){
			this.minSize = minSize;
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String[] getAllowType() {
		return allowType;
	}
	public void setAllowType(String[] allowType) {
		this.allowType = allowType;
	}
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	public Integer getMinSize() {
		return minSize;
	}
	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}
}
