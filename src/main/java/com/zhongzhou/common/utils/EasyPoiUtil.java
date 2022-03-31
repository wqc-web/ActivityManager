package com.zhongzhou.common.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @ClassName: EasyPOIUtil
 * @Description: easy poi 工具类
 * @Author: wj
 * @Date: 2020-03-08 18:03:21
 **/
public class EasyPoiUtil implements Serializable {
    private static final long serialVersionUID = 6349342581229238957L;

    /**
     * 根据模板导出文件
     * 文档地址: http://easypoi.mydoc.io/#text_197817
     *
     * @param templateFilePath 模板文件路径
     * @param map              导出数据
     * @param exportFileDir    导出文件目录
     * @param exportFileName   导出文件名称
     */
    public static void exportByTemplate(String templateFilePath, Map<String, Object> map, String exportFileDir, String exportFileName) {
        FileOutputStream fos = null;
        try {
            TemplateExportParams exportParams = new TemplateExportParams(templateFilePath);
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, map);
            File savefile = new File(exportFileDir);
            if (!savefile.exists()) {
                savefile.mkdirs();
            }
            if (exportFileName.startsWith(File.separator)) {
                exportFileName = exportFileName.substring(1);
            }
            fos = new FileOutputStream(exportFileDir + File.separator + exportFileName);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != fos){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导入excel
     * 文档地址: http://easypoi.mydoc.io/#text_197817
     *
     * @param filePath   被导入的文件路径
     * @param titleRows  表格标题行数
     * @param headerRows 表头行数
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    /**
     * 导入excel
     *
     * @param file       被导入的文件
     * @param titleRows  表格标题行数
     * @param headerRows 表头行数
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }
}
