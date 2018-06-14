package com.neo.entity;

import com.neo.enums.Body_type;

/**
 * Created by liudong on 2018/6/12.
 */
public class Bodies extends BaseEntity {

    private Body_type type;
    private String msg;             //消息内容

    private String imgUrl;          //imageUrl
    private String imageName;       //imageName

    /*图片*/
    private String thumbnailRemotePath; //缩略图
    private String originImagePath; //原始路径

    /*位置*/
    private double latitude;
    private double longitude;
    private String locationName;
    private String detailLocationName;


    private Byte[] fileData;
    private String fileName;
    private String fileRemotePath;

    /*语音*/
    private int duration;


    public Body_type getType() {
        return type;
    }

    public void setType(Body_type type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getThumbnailRemotePath() {
        return thumbnailRemotePath;
    }

    public void setThumbnailRemotePath(String thumbnailRemotePath) {
        this.thumbnailRemotePath = thumbnailRemotePath;
    }

    public String getOriginImagePath() {
        return originImagePath;
    }

    public void setOriginImagePath(String originImagePath) {
        this.originImagePath = originImagePath;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDetailLocationName() {
        return detailLocationName;
    }

    public void setDetailLocationName(String detailLocationName) {
        this.detailLocationName = detailLocationName;
    }

    public Byte[] getFileData() {
        return fileData;
    }

    public void setFileData(Byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileRemotePath() {
        return fileRemotePath;
    }

    public void setFileRemotePath(String fileRemotePath) {
        this.fileRemotePath = fileRemotePath;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
