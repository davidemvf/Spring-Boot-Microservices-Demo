package com.example.springboot.utils;


import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;

import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ProjectUtils {

    public static <T> T fromEntityToDto(Object entity, Class<T> clazz) {
        return new ModelMapper().map(entity, clazz);
    }

    public static ByteArrayDataSource zipFile(Resource resourceFile) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        ZipEntry zipEntry = new ZipEntry(Objects.requireNonNull(resourceFile.getFilename()));
        zos.putNextEntry(zipEntry);
        byte[] bytes = Files.readAllBytes(resourceFile.getFile().toPath());
        zos.write(bytes);
        zos.closeEntry();
        zos.close();
        return new ByteArrayDataSource(baos.toByteArray(), "application/zip");
    }
}
