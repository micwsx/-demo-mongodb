package com.example.demomongodb.controller;

import com.example.demomongodb.common.DateUtil;
import com.example.demomongodb.common.FileUtil;
import com.example.demomongodb.controller.viewmodel.Document;
import com.example.demomongodb.controller.viewmodel.DocumentMeta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author: Michael
 * @date: 3/15/2022 9:01 AM
 */
@Controller
@RequestMapping("/document")
public class DocumentController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/uploadDocuments", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public List<DocumentMeta> getDocument(@RequestPart(name = "docs")
                                                  List<DocumentMeta> documentMetaList,
                                          HttpServletRequest request) {
        try {
            String documentContent = objectMapper.writeValueAsString(documentMetaList);
            logger.info("list: " + documentContent);
            logger.info("request: " + request.getClass());
//            ServletContext servletContext = request.getSession().getServletContext();
            ServletContext servletContext = request.getServletContext();
            // dependency on commons-fileupload.jar
//            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(servletContext);
//            boolean multipart = commonsMultipartResolver.isMultipart(request);
            Map<String, Document> resultMap = new HashMap<>();
//            if (multipart) {// check if there are any uploading files.
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

            List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles("files");
            for (MultipartFile multipartFile : multipartFileList) {
                CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
                FileItem fileItem = commonsMultipartFile.getFileItem();
                DiskFileItem diskFileItem = (DiskFileItem) fileItem;
                File storeLocation = diskFileItem.getStoreLocation();


                String originalFilename = multipartFile.getOriginalFilename();
                String contentType = multipartFile.getContentType();
                String fileName = multipartFile.getName();
                long size = multipartFile.getSize();
//                    System.out.println("fileName: " + fileName);
//                    System.out.println("originalFilename: " + originalFilename);
//                    System.out.println("contentType: " + contentType);
//                    System.out.println("size: " + size);
                Document document = new Document();
                document.setContentType(contentType);
                document.setName(fileName);
                document.setSize(size);
                document.setOriginalName(originalFilename);
                byte[] dataBytes = multipartFile.getBytes();
                InputStream inputStream = multipartFile.getInputStream();
                // TODO: 3/29/2022 how to process the file input stream? - keep it to temporary folder.
//                    File fileDirectory = new File(FileUtil.getTempDateFolder());

                // Windows: C:\Users\Michael\AppData\Local\Temp\TEST_6174289962595444868.tmp
//                File tempFile = File.createTempFile("TEST_", ".tmp");
//                // save the file to temp folder.
//                FileUtils.copyInputStreamToFile(inputStream, tempFile);
//                logger.info("file path: " + tempFile.getAbsolutePath());

//                document.setFile(tempFile);
                if (storeLocation != null) {
                    System.out.println("storeLocation: " + storeLocation.getAbsolutePath());
                    document.setFile(storeLocation);
                }
                document.setContent(dataBytes);
                resultMap.put(originalFilename, document);
            }
//            }

            for (DocumentMeta documentMeta : documentMetaList) {
                Document document = resultMap.get(documentMeta.getDocumentName());
                if (document != null) {
                    document.setDocumentId(UUID.randomUUID().toString().replace("-", ""));
                    // exist physical file
                    documentMeta.setDocument(document);
                }
            }
            return documentMetaList;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    @RequestMapping(value = "/uploadDocuments1", method = RequestMethod.POST)
    @ResponseBody
    public List<Document> getDocument1(@RequestPart(name = "docs") List<DocumentMeta> documentMetaList,
                                       @RequestParam(name = "files") List<MultipartFile> multipartFiles
            , @RequestParam(name = "test") String moduleName) {
        try {
            System.out.println("moduleName: " + moduleName);
            System.out.println("documentMetaList: " + objectMapper.writeValueAsString(documentMetaList));
            if (multipartFiles != null && multipartFiles.size() > 0) {
                System.out.println("count: " + multipartFiles.size());
                for (MultipartFile multipartFile : multipartFiles) {
                    String originalFilename = multipartFile.getOriginalFilename();
                    String fileName = multipartFile.getName();
                    String contentType = multipartFile.getContentType();
                    long size = multipartFile.getSize();

                    System.out.println("fileName: " + fileName);
                    System.out.println("originalFilename: " + originalFilename);
                    System.out.println("contentType: " + contentType);
                    System.out.println("size: " + size);
//                    try {
//                        InputStream inputStream = multipartFile.getInputStream();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
