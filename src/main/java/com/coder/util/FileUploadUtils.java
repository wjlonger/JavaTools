package com.coder.util;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FileUploadUtils {

    private String tempPath = null;

    private String dstPath = null;

    private String newFileName = null;

    private HttpServletRequest fileuploadReq = null;

    private int sizeThreshold = 4096;

    private long sizeMax = 10485760;

    private int picSeqNo = 1;

    private boolean isSmallPic = false;

    public FileUploadUtils(String tempPath, String destinationPath) {
        this.tempPath = tempPath;
        this.dstPath = destinationPath;
    }

    public FileUploadUtils(String tempPath, String destinationPath, HttpServletRequest fileuploadRequest) {
        this.tempPath = tempPath;
        this.dstPath = destinationPath;
        this.fileuploadReq = fileuploadRequest;
    }

    public final boolean Upload() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        try {
            // FileUtil.makeDirectory(dstPath + "/ddd");
            if (!FileUtil.makeDirectory(dstPath+"/ddd")) {
                throw new IOException("Create destination Directory Error.");
            }
            //FileUtil.makeDirectory(tempPath + "/ddd");
            if (!FileUtil.makeDirectory(tempPath+"/ddd")) {
                throw new IOException("Create Temp Directory Error.");
            }

            factory.setSizeThreshold(sizeThreshold);
            factory.setRepository(new File(tempPath));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(sizeMax);

            List fileItems = null;
            try {
                fileItems = upload.parseRequest(fileuploadReq);
            } catch (org.apache.commons.fileupload.FileUploadException e) {
                e.printStackTrace();
            }
            // assume we know there are two files. The first file is a small
            // text file, the second is unknown and is written to a file on
            // the server
            Iterator iter = fileItems.iterator();
            String regExp = ".+\\\\(.+)$";
            String[] errorType = {".exe", ".com", ".cgi", ".asp", ".php", ".jsp"};
            Pattern p = Pattern.compile(regExp);
            while (iter.hasNext()) {
                System.out.println("++00++=====" + newFileName);
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    String name = item.getName();
                    System.out.println("++++=====" + name);
                    long size = item.getSize();
                    if ((name == null || name.equals("")) && size == 0) {
                        continue;
                    }
                    Matcher m = p.matcher(name);
                    boolean result = m.find();
                    if (result) {
                        for (int temp = 0; temp < errorType.length; temp++) {
                            if (m.group(1).endsWith(errorType[temp])) {
                                throw new IOException(name + ": Wrong File Type");
                            }
                        }
                        String ext = "." + FileUtil.getTypePart(name);
                        try {
                            if (newFileName == null || newFileName.trim().equals("")) {
                                item.write(new File(dstPath + "/" + m.group(1)));
                            } else {
                                String uploadfilename = "";
                                if (isSmallPic) {
                                    uploadfilename = dstPath + "/" + newFileName + "_" + picSeqNo + "_small" + ext;
                                } else {
                                    uploadfilename = dstPath + "/" + newFileName + "_" + picSeqNo + ext;
                                }
                                System.out.println("++++=====" + uploadfilename);
                                FileUtil.makeDirectory(uploadfilename);
                                item.write(new File(uploadfilename));
                            }
                            picSeqNo++;
                        } catch (Exception e) {
                            throw new IOException(e.getMessage());
                        }
                    } else {
                        throw new IOException("fail to upload");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return true;
    }

    public final String GetFileName(String filepath) {
        String returnStr = "*.*";
        int length = filepath.trim().length();
        filepath = filepath.replace('\\', '/');
        if (length > 0) {
            int i = filepath.lastIndexOf("/");
            if (i >= 0) {
                filepath = filepath.substring(i + 1);
                returnStr = filepath;
            }
        }
        return returnStr;
    }

    public void setTmpPath(String tmppath) {
        this.tempPath = tmppath;
    }

    public void setDstPath(String dstpath) {
        this.dstPath = dstpath;
    }

    public void setFileMaxSize(long maxsize) {
        this.sizeMax = maxsize;
    }

    public void setHttpReq(HttpServletRequest httpreq) {
        this.fileuploadReq = httpreq;
    }

    public void setNewFileName(String filename) {
        this.newFileName = filename;
    }

    public void setIsSmalPic(boolean isSmallPic) {
        this.isSmallPic = isSmallPic;
    }

    public void setPicSeqNo(int seqNo) {
        this.picSeqNo = seqNo;
    }

}
