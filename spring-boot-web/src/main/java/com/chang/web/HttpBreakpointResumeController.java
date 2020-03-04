package com.chang.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

@Slf4j
@RestController
public class HttpBreakpointResumeController {

    private String file = "spring-boot-web/src/main/resources/test.png";
    private Long limit = 1033L;
    private Long breakpoint = 0L;

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void files(HttpServletRequest request, HttpServletResponse response) {
        String range = request.getHeader("Range");
        if (StringUtils.isEmpty(range)) {
            passSegment(response, 0L);
        } else {
            passSegment(response, breakpoint);
            response.setStatus(HttpStatus.PARTIAL_CONTENT.value());
            String ranges = "";
            response.addHeader("Content-Ranges", ranges);
        }
    }

    public void passSegment(HttpServletResponse response, Long beginIdx) {
        response.setHeader("Content-Disposition", "attachment;filename=" + file);
        RandomAccessFile randomFile = null;
        OutputStream outputStream = null;
        try {
            randomFile = readFileByRandomAccess(file, beginIdx);
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[1024];
            int n;
            Long readTotal = 0L;
            while ((n = randomFile.read(buff)) != -1) {
                outputStream.write(buff, 0, n);
                readTotal += n;
                if (readTotal > limit) {
                    breakpoint = readTotal;
                    System.out.println("readTotal: " + breakpoint);
                    break;
                }
            }
            outputStream.flush();
        } catch (Exception ex) {
            log.error("download file encounters IOException.", ex);
        } finally {
            try {
                if (null != randomFile) {
                    randomFile.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (Exception ex) {
                log.error("stream close failed.", ex);
            }
        }
    }

    /**
     * 随机读取文件内容
     * 读文件的起始位置
     */
    public RandomAccessFile readFileByRandomAccess(String fileName, Long beginIndex) throws Exception {
        RandomAccessFile randomFile = null;
        System.out.println("随机读取一段文件内容：");
        // 打开一个随机访问文件流，按只读方式
        randomFile = new RandomAccessFile(fileName, "r");
        // 文件长度，字节数
        long fileLength = randomFile.length();
        // 将读文件的开始位置移到beginIndex位置。
        randomFile.seek(beginIndex);
        return randomFile;
    }

}
