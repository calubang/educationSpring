package com.kitri.cafe.board.model;

public class BbsDto extends BoardDto{
	
	private int aseq;
	private String orignFile;
	private String saveFile;
	private long filesize;
	private String saveFolder;
	
	public int getAseq() {
		return aseq;
	}
	public void setAseq(int aseq) {
		this.aseq = aseq;
	}
	public String getOrignFile() {
		return orignFile;
	}
	public void setOrignFile(String orignFile) {
		this.orignFile = orignFile;
	}
	public String getSaveFile() {
		return saveFile;
	}
	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	@Override
	public String toString() {
		return "BbsDto [aseq=" + aseq + ", orignFile=" + orignFile + ", saveFile=" + saveFile + ", filesize=" + filesize
				+ ", saveFolder=" + saveFolder + "]";
	}
}
