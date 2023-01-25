/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package ericzz.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
public class ExcelUtils {


    public static String format(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        } else {
            return null;
        }
    }

    /**
     * Excel导出
     *
     * @param response  response
     * @param fileName  文件名
     * @param list      数据List
     * @param pojoClass 对象Class
     */
    public static void exportExcel(HttpServletResponse response, String fileName, Collection<?> list,
                                   Class<?> pojoClass, String title) throws IOException {
        if (org.apache.commons.lang3.StringUtils.isEmpty(fileName)) {
            //当前日期
            fileName = ExcelUtils.format(new Date());
        }

        ExportParams ep = new ExportParams();
        ep.setTitle(title);
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, list);
        Workbook workbook = ExcelExportUtil.exportExcel(ep, pojoClass, list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * Excel导出，先sourceList转换成List<targetClass>，再导出
     *
     * @param response    response
     * @param fileName    文件名
     * @param sourceList  原数据List
     * @param targetClass 目标对象Class
     */
    public static void exportExcelToTarget(HttpServletResponse response, String fileName, Collection<?> sourceList,
                                           Class<?> targetClass, String title) throws Exception {
        List targetList = new ArrayList<>(sourceList.size());
        for (Object source : sourceList) {
            Object target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        exportExcel(response, fileName, targetList, targetClass, title);
    }

    /**
     * @param outputStream
     * @param fileName
     * @param list
     * @param pojoClass
     * @param title
     * @throws Exception
     */
    public static void exportExcelToOutStream(OutputStream outputStream, String fileName, Collection<?> list,
                                              Class<?> pojoClass, String title) throws Exception {
        List targetList = new ArrayList<>(list.size());
        for (Object source : list) {
            Object target = pojoClass.newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        ExportParams ep = new ExportParams();
        if (StringUtils.isNotBlank(title)) {
            ep.setTitle(title);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(ep, pojoClass, targetList);
        workbook.write(outputStream);
        outputStream.flush();
    }

    /**
     * 读取上传的excel返回List
     *
     * @param file
     * @param inputStream
     * @param list
     * @return
     */
    public static List<Map<String, Object>> readFile(MultipartFile file, InputStream inputStream,
                                                     List<Map<String, Object>> list) {
        try {
            inputStream = file.getInputStream();
            ImportParams importParams = new ImportParams();
            list = ExcelImportUtil.importExcel(inputStream, Map.class, importParams);
        } catch (IOException e) {
            log.info(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.info(e.getMessage());
                }
            }
        }
        return list;
    }
}
