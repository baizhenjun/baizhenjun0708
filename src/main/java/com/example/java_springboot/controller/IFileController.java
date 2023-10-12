package com.example.java_springboot.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.java_springboot.util.DateUtil;
import com.example.java_springboot.util.Func;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;
import com.example.java_springboot.util.file.*;

@Api(value = "文件操作API")
@Slf4j
@RestController
@RequestMapping("/ifile")
public class IFileController {

    @Value("${audit.upload.file.path.prefix}")
    private String filePathPrefix;

    @ApiOperation("文件上传File")
    @PostMapping(value = "/file/save")
    public void save(@ApiParam("上传的文件") @RequestPart(value = "file") MultipartFile file) throws Exception {
        // 文件上传
        String targetPath = filePathPrefix + "/" + DateUtil.getCurrentYMD() + "/";
        // 文件名生成
        List<String> fileNames = FileUtils.uploadMultiFile(new MultipartFile[]{file}, targetPath);
    }

    @ApiOperation("文件下载-by文件id")
    @GetMapping("/download/fileid")
    public void downloadByFileId(@RequestParam("fileUuid") String fileUuid, HttpServletResponse response){
        // 获取文件地址
        String path = "D:\\photo\\001.JPG";
        try(InputStream in = new FileInputStream(path);
            OutputStream out = response.getOutputStream()) {
            response.setContentType("application/octet-stream;charset=utf-8");
            // 获取文件名称
            String fileName = "001";
            if(".pdf".equals(fileName.toLowerCase())){
                // pdf预览
                response.setHeader("Content-Type", "application/pdf");
            }else{
                response.setHeader("Content-Disposition", "attachment;fileName=\"" + new String("001".getBytes("gb2312"), "ISO8859-1")+"\"");
            }
            IOUtils.copy(in, out);
            //不加out.flush会导致后台被执行2次(解决window.location文件下载会执行2次 window.location.href多次触发问题)
            out.flush();
        } catch (IOException e) {
            try {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("文件不存在，下载失败");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            log.error("fileUuid：" + fileUuid + "-下载失败", e);
        }
    }

    @ApiOperation("文件下载--by文件id")
    @GetMapping("a/download/fileid")
    public void downloadsByFileId(@RequestParam("fileUuid") String fileUuid, HttpServletResponse response){
        List<Object> iFileList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(iFileList)){
            // 直接 下载文件
            FileUtils.download("D:\\photo\\001.JPG","001",response);
            return;
        }
        try(ZipOutputStream out = new ZipOutputStream(response.getOutputStream())) {
            response.setContentType("application/octet-stream;charset=utf-8");
            //不加out.flush会导致后台被执行2次(解决window.location文件下载会执行2次 window.location.href多次触发问题)
            out.flush();
        } catch (IOException e) {
            try {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("文件不存在，下载失败");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            log.error("fileUuid：" + fileUuid + "-下载失败", e);
        }

    }

    @ApiOperation("文件下载-by业务id")
    @GetMapping("a/download/serviceid")
    public void downloadsByServiceId(@RequestParam("serviceId")String serviceId, HttpServletResponse response){
        List<Object> iFileList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(iFileList)&&iFileList.size()==1){
            // 直接 下载文件
            FileUtils.download("D:\\photo\\001.JPG","001",response);
            return;
        }
        try(ZipOutputStream out = new ZipOutputStream(response.getOutputStream())) {
            response.setContentType("application/octet-stream;charset=utf-8");
            // todo 压缩包名称待定
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String("temp.zip".getBytes("gb2312"), "ISO8859-1"));
            iFileList.forEach(file -> {
                try {
                    // 按文件名字压缩
                    ZipUtil.doCompress2("D:\\photo\\001.JPG", out,"001");
                    response.flushBuffer();
                } catch (IOException e) {
                    try {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().print("文件不存在，下载失败");
                    } catch (IOException ioException) {
                        log.error("serviceId：" + serviceId + "-下载失败", e);
                    }
                }
            });
        } catch (Exception e) {
            try {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("文件不存在，下载失败");
            } catch (IOException ioException) {
                log.error("serviceId：" + serviceId + "-下载失败", e);
            }
            log.error("serviceId：" + serviceId + "-下载失败", e);
        }

    }

    @ApiOperation("查看图片")
    @RequestMapping("/lookPicture")
    public void findPersonImg(HttpServletResponse response, String fileUuid) {
        if (Func.notNull("D:\\photo\\001.JPG")) {
            OutputStream os = null;
            try (
              InputStream is = new FileInputStream("D:\\photo\\001.JPG");
            ) {
                byte[] bytes = new byte[1024 * 4];
                while (is.read(bytes) != -1) {
                    os = response.getOutputStream();
                    os.write(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}



