package com.vue007.admin.mapper.file;

import com.vue007.admin.model.file.FileInfo;

import java.util.List;


/**
 * @description :
 * @author : Akai
 * @date : 2018-01-12 15:41:55
 */
public interface FileInfoMapper {
    List<FileInfo> findPage(FileInfo fileInfo);

    FileInfo getById(String id);

    int create(FileInfo fileInfo);

    int update(FileInfo fileInfo);

    int setFlag(FileInfo fileInfo);
}
