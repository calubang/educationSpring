package com.kitri.cafe.board.model;

public class AlbumDto extends BoardDto{
	private int aseq;
	private String orignPicture;
	private String savePicture;
	private String saveFolder;
	private String type;
	
	public int getAseq() {
		return aseq;
	}
	public void setAseq(int aseq) {
		this.aseq = aseq;
	}
	public String getOrignPicture() {
		return orignPicture;
	}
	public void setOrignPicture(String orignPicture) {
		this.orignPicture = orignPicture;
	}
	public String getSavePicture() {
		return savePicture;
	}
	public void setSavePicture(String savePicture) {
		this.savePicture = savePicture;
	}
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AlbumDto [aseq=" + aseq + ", orignPicture=" + orignPicture + ", savePicture=" + savePicture
				+ ", saveFolder=" + saveFolder + ", type=" + type + "]";
	}

}
