package com.zhongzhou.api.common;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongzhou.api.entity.Attachment;
import com.zhongzhou.api.entity.Dictionary;
import com.zhongzhou.api.service.impl.DictionaryServiceImpl;
import com.zhongzhou.common.base.BaseController;
import com.zhongzhou.common.bean.ReturnEntity;
import com.zhongzhou.common.bean.ReturnEntityError;
import com.zhongzhou.common.bean.ReturnEntitySuccess;
import com.zhongzhou.common.utils.Constants;
import com.zhongzhou.common.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

/**
 * @ClassName: UploadController
 * @Description: 附件上传
 * @Author: wj
 * @Date: 2020-06-28 16:40:06
 **/
@RestController
@RequestMapping("/api")
@Slf4j
public class UploadController extends BaseController {
    private static final long serialVersionUID = 6087390561442587655L;
    private static final String UPLOADFILE_TYPE_IMG = "img";
    private static final String UPLOADFILE_TYPE_VIDEO = "video";
    private static final String UPLOADFILE_TYPE_AUDIO = "audio";
    private static final String UPLOADFILE_TYPE_EXCEL = "excel";
    private static final String UPLOADFILE_TYPE_WORD = "word";
    private static final String UPLOADFILE_TYPE_PDF = "pdf";
    private static final String UPLOADFILE_TYPE_PPT = "ppt";
    /**
     * 图片后缀
     */
    private static final String IMAGE_SUFFIX = "jpg|png|gif|bmp|jpeg";
    /**
     * 视频后缀
     */
    private static final String VIDEO_SUFFIX = "mp4|mov|webm|ogg";
    /**
     * 音频后缀
     */
    private static final String AUDIO_SUFFIX = "mp3|wav|ogg";
    /**
     * excel后缀
     */
    private static final String EXCEL_SUFFIX = "xls|xlsx|xlsm";
    /**
     * word后缀
     */
    private static final String WORD_SUFFIX = "doc|docx";
    /**
     * ppt后缀
     */
    private static final String PPT_SUFFIX = "ppt|pptx";
    /**
     * pdf后缀
     */
    private static final String PDF_SUFFIX = "pdf";

    @Resource
    private TokenController tokenController;
    @Resource
    private DictionaryServiceImpl dictionaryService;

    @Value("${constants.file_src}")
    private String fileSrc;
    @Value("${constants.file_path}")
    private String realPath;

    /**
     * 附件上传
     *
     * @param file       附件
     * @param typeCode   类型编码
     * @param sourceId   关联资源ID
     * @param fileType   附件类型
     * @param fileLength 图片大小
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @return ReturnEntity
     */
    @PostMapping("/upload")
    public ReturnEntity uploadFile(@RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "typeCode", required = false) String typeCode,
                                   @RequestParam(value = "sourceId", required = false) Long sourceId,
                                   @RequestParam(value = "fileType", required = false, defaultValue = "") String fileType,
                                   @RequestParam(value = "fileLength", required = false, defaultValue = "10") Integer fileLength,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
//            String realPath = request.getSession().getServletContext().getRealPath("/");
            String contextPath = request.getContextPath();
            System.out.println("contextPath=" + contextPath);
//            String realPath = filePath;
            String filePath = FileUploadUtil.createFileCatalog(realPath, "upload");
            String originalFileName = file.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            String url = filePath + SecureUtil.simpleUUID() + suffix;
            if (StringUtils.isBlank(fileType)) {
                if (IMAGE_SUFFIX.contains(suffix.substring(1).toLowerCase())) {
                    fileType = UPLOADFILE_TYPE_IMG;
                } else if (VIDEO_SUFFIX.contains(suffix.substring(1).toLowerCase())) {
                    fileType = UPLOADFILE_TYPE_VIDEO;
                } else if (AUDIO_SUFFIX.contains(suffix.substring(1).toLowerCase())) {
                    fileType = UPLOADFILE_TYPE_AUDIO;
                } else if (EXCEL_SUFFIX.contains(suffix.substring(1).toLowerCase())) {
                    fileType = UPLOADFILE_TYPE_EXCEL;
                } else if (WORD_SUFFIX.contains(suffix.substring(1).toLowerCase())) {
                    fileType = UPLOADFILE_TYPE_WORD;
                } else if (PDF_SUFFIX.contains(suffix.substring(1).toLowerCase())) {
                    fileType = UPLOADFILE_TYPE_PDF;
                } else if (PPT_SUFFIX.contains(suffix.substring(1).toLowerCase())) {
                    fileType = UPLOADFILE_TYPE_PPT;
                } else {
                    fileType = "";
                }
            }

            if (file.isEmpty()) {
                return new ReturnEntityError("附件不能为空");
            } else if (file.getSize() > 1024 * 1024 * fileLength) {
                return new ReturnEntityError("附件大小超过限制");
            } else if (!FileUploadUtil.checkFileSuffix(fileType, originalFileName.substring(originalFileName.lastIndexOf(".") + 1))) {
                return new ReturnEntityError("附件格式错误");
            } else {
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(realPath + url));
                //图片压缩
//                Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.5f).toFile(realPath + url);
                Attachment attachment = new Attachment();
                attachment.setName(originalFileName);
                //根据数据字典设置type
                if (StringUtils.isNotBlank(typeCode)) {
                    QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
                    dictionaryQueryWrapper.eq("dic_code", typeCode);
                    dictionaryQueryWrapper.eq("type_code", "attachmentType");
                    Dictionary dictionary = dictionaryService.getOne(dictionaryQueryWrapper);
                    attachment.setType(dictionary.getId());
                    attachment.setTypeName(dictionary.getDicName());
                } else {
                    attachment.setType(Constants.NUM_ZERO);
                }
                attachment.setUrl(url);
                if (null != sourceId) {
                    attachment.setSourceId(sourceId);
                }
                if (StringUtils.isNotBlank(request.getHeader("Authorization"))) {
                    attachment.setCreateUserId(tokenController.getUserId(request, response));
                } else {
                    attachment.setCreateUserId(1L);
                }
                attachment.setCreateTime(LocalDateTime.now());
                if (attachment.insert()) {
                    return new ReturnEntitySuccess("上传成功", attachment);
                } else {
                    return new ReturnEntityError("上传失败");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ReturnEntityError("上传失败");
        }
    }

    /**
     * 富文本中附件上传
     *
     * @param file     附件
     * @param typeCode 类型编码
     * @param sourceId 来源
     * @return ReturnEntity
     */
    @PostMapping("/articleUpload")
    public ReturnEntity articleUpload(@RequestParam("file") MultipartFile file,
                                      @RequestParam(value = "typeCode", required = false) String typeCode,
                                      @RequestParam(value = "sourceId", required = false) Long sourceId) {
        try {
//            String realPath = request.getSession().getServletContext().getRealPath("/");
//            String realPath = filePath;
            String filePath = FileUploadUtil.createFileCatalog(realPath, "upload");
            String originalFileName = file.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            String url = filePath + SecureUtil.simpleUUID() + suffix;

            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(realPath + url));
            Attachment attachment = new Attachment();
            attachment.setName(originalFileName);
            //根据数据字典设置type
            if (StringUtils.isNotBlank(typeCode)) {
                QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
                dictionaryQueryWrapper.eq("dic_code", typeCode);
                dictionaryQueryWrapper.eq("type_code", "attachmentType");
                Dictionary dictionary = dictionaryService.getOne(dictionaryQueryWrapper);
                attachment.setType(dictionary.getId());
                attachment.setTypeName(dictionary.getDicName());
            } else {
                attachment.setType(Constants.NUM_ZERO);
            }
            attachment.setUrl(url);
            if (null != sourceId) {
                attachment.setSourceId(sourceId);
            }
            attachment.setCreateUserId(1L);
            attachment.setCreateTime(LocalDateTime.now());

            if (attachment.insert()) {
                JSONObject object = new JSONObject();
                object.put("src", fileSrc + attachment.getUrl());
                object.put("title", attachment.getName());
                return new ReturnEntitySuccess("上传成功", object);
            } else {
                return new ReturnEntityError("上传失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ReturnEntityError("上传失败");
        }
    }

    /**
     * 上传附件，返回URL
     *
     * @param file     附件
     * @param typeCode 类型编码
     * @return ReturnEntity
     */
    @PostMapping("/uploadWithURL")
    public ReturnEntity uploadWithURL(@RequestParam("file") MultipartFile file, @RequestParam(value = "typeCode", required = false) String typeCode) {
        try {
//            String realPath = filePath;
            String filePath = FileUploadUtil.createFileCatalog(realPath, "upload");
            String originalFileName = file.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            String url = filePath + SecureUtil.simpleUUID() + suffix;
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(realPath + url));
            Attachment attachment = new Attachment();
            attachment.setName(originalFileName);
            //根据数据字典设置type
            if (StringUtils.isNotBlank(typeCode)) {
                QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
                dictionaryQueryWrapper.eq("dic_code", typeCode);
                dictionaryQueryWrapper.eq("type_code", "attachmentType");
                Dictionary dictionary = dictionaryService.getOne(dictionaryQueryWrapper);
                attachment.setType(dictionary.getId());
                attachment.setTypeName(dictionary.getDicName());
            } else {
                attachment.setType(Constants.NUM_ZERO);
            }
            attachment.setUrl(url);
            attachment.setCreateUserId(1L);
            attachment.setCreateTime(LocalDateTime.now());

            if (attachment.insert()) {
                return new ReturnEntitySuccess("上传成功", attachment.getUrl());
            } else {
                return new ReturnEntityError("上传失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ReturnEntityError("上传失败");
        }
    }
}
