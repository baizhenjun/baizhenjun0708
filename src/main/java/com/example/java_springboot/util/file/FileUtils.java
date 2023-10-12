package com.example.java_springboot.util.file;


import com.example.java_springboot.util.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class FileUtils {

    /**
     * 获取 根路径
     * todo linux 待测试
     * @return
     */
    public static String getRootPath(){
        String rootPath="";
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            File directory = new File("..");
            try {
                rootPath = directory.getCanonicalPath();
            } catch (IOException e) {
                log.error("文件根路径获取异常：",e);
            }
        }
        return rootPath;
    }

    /*判断文件是否存在*/
    public static boolean isExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /*判断是否是文件夹*/
    public static boolean isDir(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.isDirectory();
        } else {
            return false;
        }
    }


    public static boolean isDirCreate(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        } else {
            return false;
        }
    }

    /**
     * 文件或者目录重命名
     *
     * @param oldFilePath 旧文件路径
     * @param newName     新的文件名,可以是单个文件名和绝对路径
     * @return
     */
    public static boolean renameTo(String oldFilePath, String newName) {
        try {
            File oldFile = new File(oldFilePath);
            //若文件存在
            if (oldFile.exists()) {
                //判断是全路径还是文件名
                if (newName.indexOf("/") < 0 && newName.indexOf("\\") < 0) {
                    //单文件名，判断是windows还是Linux系统
                    String absolutePath = oldFile.getAbsolutePath();
                    if (newName.indexOf("/") > 0) {
                        //Linux系统
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("/") + 1) + newName;
                    } else {
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("\\") + 1) + newName;
                    }
                }
                File file = new File(newName);
                //判断重命名后的文件是否存在
                if (file.exists()) {
                    System.out.println("该文件已存在,不能重命名");
                } else {
                    //不存在，重命名
                    return oldFile.renameTo(file);
                }
            } else {
                System.out.println("原该文件不存在,不能重命名");
            }
        } catch (Exception e) {

            log.error("", e.getMessage(), e);
        }
        return false;
    }


    /*文件拷贝操作*/
    public static void copy(String sourceFile, String targetFile) {
        File source = new File(sourceFile);
        File target = new File(targetFile);
        target.getParentFile().mkdirs();
        try (
                FileInputStream fis = new FileInputStream(source);
                FileOutputStream fos = new FileOutputStream(target);
                FileChannel in = fis.getChannel();//得到对应的文件通道
                FileChannel out = fos.getChannel();//得到对应的文件通道
        ) {
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {

            log.error("", e.getMessage(), e);
        }
    }

    /*读取Text文件操作*/
    public static String readText(String filePath) {
        String lines = "";

        try (
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines += line + "\n";
            }
        } catch (IOException e) {

            log.error("", e.getMessage(), e);
        }
        return lines;
    }

    /*写入Text文件操作*/
    public static void writeText(String filePath, String content, boolean isAppend) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = new FileOutputStream(filePath, isAppend);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
        } catch (IOException e) {

            log.error("", e.getMessage(), e);
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {

                log.error("", e.getMessage(), e);
            }
        }
    }


    /**
     * 多文件上传
     *
     * @param files
     * @param fileTargetPath
     * @return
     */
    public static List<String> uploadMultiFile(MultipartFile[] files, String fileTargetPath) {
        List<String> urlList = new ArrayList<>();
        String rootPath = getRootPath();
        fileTargetPath=rootPath+fileTargetPath;
        if (!isExists(fileTargetPath)){
            mkdirs(fileTargetPath);
        }
        try {
            for (MultipartFile file : files) {
                if (file.getSize() > 0) {
                    String originalFilename = file.getOriginalFilename(); //获取图片原来名称
                    // 文件在服务器上只存uuid
                    String filePathName = fileTargetPath + UuidUtils.getUUID() + "." + FilenameUtils.getExtension(originalFilename); //真实的图片保存相对路径
                    file.transferTo(new File(filePathName));
                    urlList.add(filePathName);
                }
            }


        } catch (Exception e) {
            log.error("多文件上传异常{}", e.getMessage(), e);
        }
        return urlList;
    }

    /**
     * 将本地文件上传到oss系统
     *
     * @param localFilePath 本地文件目录路径
     * @param ossFilePath   oss目录路径
     * @return
     */
  /*  public static BaseResponse upload(String boeEncryptionUri,String localFilePath, String ossFilePath) {
        BaseResponse responseMap = null;
        System.out.println(boeEncryptionUri);
        try {
            File[] files = fileSort(localFilePath);
            List<String> imageUrlList = new ArrayList();// AliOssUtils.saveFileBatch(ossFilePath,files);//将图片上传到oss服务器
            System.out.println("上传oss完成");
            responseMap = BaseResponse.success(imageUrlList);
        } catch (Exception e) {

            log.error("",e.getMessage(),e);
            responseMap = BaseResponse.error(ResultEnum.FILE_UPLOAD_FAILED.getCode()
                    , ResultEnum.FILE_UPLOAD_FAILED.getMessage());
            return responseMap;
        }
        return responseMap;
    }*/


    /**
     * 汪敏 2017-12-20
     * 服务器通用图片批量上传
     *
     * @param picture    大文本对象数组
     * @param mp4TempDir 文件保存全路径
     * @return
     */
    public static boolean uploadLocal(MultipartFile[] picture, String mp4TempDir) {
        boolean bool = false;
        try {
            for (int i = 0; i < picture.length; i++) {
                if (picture[i].getSize() > 0) {
                    String originalFilename = picture[i].getOriginalFilename(); //获取图片原来名称
                    String filePathName = mp4TempDir + i + "." + FilenameUtils.getExtension(originalFilename); //真实的图片保存相对路径
                    picture[i].transferTo(new File(filePathName));
                }
            }
            bool = true;
            System.out.println("图片上传到服务器完成");
        } catch (Exception e) {

            log.error("", e.getMessage(), e);
        }
        return bool;
    }

    /**
     * 通过上一层目录和目录名得到最后的目录层次
     *
     * @param previousDir 上一层目录
     * @param dirName     当前目录名
     * @return
     */
    public static String getSaveDir(String previousDir, String dirName) {
        if (StringUtils.isNotBlank(previousDir)) {
            dirName = previousDir + "/" + dirName + "/";
        } else {
            dirName = dirName + "/";
        }
        return dirName;
    }

    /**
     * 如果目录不存在，就创建文件
     *
     * @param dirPath
     * @return
     */
    public static String mkdirs(String dirPath) {
        try {
            File file = new File(dirPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {

            log.error("", e.getMessage(), e);
        }
        return dirPath;
    }


    /**
     * 在Linux系统中读取文件时将文件排序
     *
     * @param filePath
     * @return
     */
 /*   public static File[] fileSort(String filePath) {
        File[] files = new File(filePath).listFiles();
        int filesLength = files.length;
        String nextFix = FilenameUtils.getExtension(files[0].getName());
        File[] fileNames = new File[filesLength];
        for (int i = 0; i < filesLength; i++) {
            for (int j = 0; j < filesLength; j++) {
                String absolutePath = files[j].getAbsolutePath();
                if (absolutePath.endsWith("/" + i + "." + nextFix) || absolutePath.endsWith("\\" + i + "." + nextFix)) {
                    fileNames[i] = new File(absolutePath);
                    break;
                }
            }
        }
        return fileNames;
    }*/


    /**
     * 普通文件下载，文件在服务器里面
     *
     * @param request
     * @param response
     */
    public static void download(HttpServletRequest request, HttpServletResponse response) {
        String realPath = request.getServletContext().getRealPath("/");
        realPath = realPath + "index.jsp";
        //System.out.println("下载地址=" + realPath);
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(realPath));
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        ) {
            String downName = realPath.substring(realPath.lastIndexOf("/") + 1);
            //System.out.println("下载文件名=" + downName);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downName, "utf-8"));
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error("文件下载异常：{}", e.getMessage());
            //System.out.println("下载出错");
        }
    }

    /**
     * 普通文件下载，文件路径固定
     * 文件下载方式： 流的方式 、 下载到本地 、下载网络文件 、支持在线打开的方式(header--Content-Disposition设置为 inline)
     *
     * @param targetFile 下载的文件路径
     * @param response
     */
    public static void download(String targetFile, String fileName,HttpServletResponse response) {
        log.info("下载文件路径:{}", targetFile);
        try (
                //设置文件下载时，文件流的格式
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(targetFile));
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        ) {
            //下面这个变量保存的是要下载的文件拼接之后的完整路径
            String downName = targetFile.substring(targetFile.lastIndexOf("/") + 1);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error("指定文件下载路径下载异常：{}", e.getMessage(), e);
        }
    }

    /**
     * 下载网络文件
     *
     * @param targetFile
     * @param response
     */
    public static void downloadUrl(String targetFile, HttpServletResponse response) {
        try (
                FileOutputStream fos = new FileOutputStream("D:/img/1.zip");//例如：test.txt
        ) {
            URL website = new URL(targetFile);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {

            log.error("", e.getMessage(), e);
            System.out.println("下载出错");
        }
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */

    public static boolean delete(String fileName) {
        try {
            File sourceFile = new File(fileName);
            if (sourceFile.isDirectory()) {
                for (File listFile : sourceFile.listFiles()) {
                    delete(listFile.getAbsolutePath());
                }
            }
            return sourceFile.delete();
        } catch (Exception e) {

            log.error("", e.getMessage(), e);
        }
        return false;
    }
}