package com.desksoft.entity.mapper;

import com.desksoft.entity.UploadPic;

public interface UploadPicMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table upload_pic
     *
     * @mbggenerated Sun Jul 14 01:45:43 CST 2013
     */
    int deleteByPrimaryKey(String picId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table upload_pic
     *
     * @mbggenerated Sun Jul 14 01:45:43 CST 2013
     */
    int insert(UploadPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table upload_pic
     *
     * @mbggenerated Sun Jul 14 01:45:43 CST 2013
     */
    int insertSelective(UploadPic record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table upload_pic
     *
     * @mbggenerated Sun Jul 14 01:45:43 CST 2013
     */
    UploadPic selectByPrimaryKey(String picId);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table upload_pic
     *
     * @mbggenerated Sun Jul 14 01:45:43 CST 2013
     */
    int updateByPrimaryKeySelective(UploadPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table upload_pic
     *
     * @mbggenerated Sun Jul 14 01:45:43 CST 2013
     */
    int updateByPrimaryKey(UploadPic record);
}