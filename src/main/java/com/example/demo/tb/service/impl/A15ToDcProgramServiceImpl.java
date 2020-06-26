package com.example.demo.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.tb.entity.A15ToDcProgram;
import com.example.demo.tb.mapper.A15ToDcProgramMapper;
import com.example.demo.tb.service.IA15ToDcProgramService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gyf
 * @since 2020-06-24
 */
@Service
public class A15ToDcProgramServiceImpl extends ServiceImpl<A15ToDcProgramMapper, A15ToDcProgram> implements IA15ToDcProgramService {
    private static final Logger LOGGER = LoggerFactory.getLogger(A15ToDcProgramServiceImpl.class);

    /**
     * 1. 缺少日志表
     * 2. 表是否需要重新设计 一条账簿ID 是否只有一条记录
     * @throws IOException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void analysis() throws IOException {
        LOGGER.info("文件解析开始:{}", LocalDateTime.now());
        String path = "C:\\Users\\FuN\\Desktop\\base_data";
        File directory = new File(path);
        if (!directory.isDirectory()) {
            LOGGER.error("找不到目录:{}", path);
            return;
        }
        File[] files = directory.listFiles();
        if(files==null){
            LOGGER.error("目录下不存在文件,目录位置:{}", directory.getAbsoluteFile());
            return;
        }
        LOGGER.info("当前目录下共有待处理文件:{}", files.length);
        int successTotal = 0;
        int failTotal = 0;
        for (File file : files) {
            try {
                if(!file.isFile()){
                    LOGGER.warn("忽略目录文件");
                    continue;
                }
                if(!file.getName().endsWith(".txt")){
                    LOGGER.warn("忽略非txt结尾文件");
                    continue;
                }
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String str;
                List<A15ToDcProgram> insertList = Lists.newArrayList();
                List<A15ToDcProgram> updateList = Lists.newArrayList();
                LOGGER.info("开始读取文件，文件名称为:{}", file.getName());
                while ((str = bufferedReader.readLine()) != null) {
                    A15ToDcProgram data = new A15ToDcProgram();
                    if (StringUtils.isNotBlank(str)) {
                        String[] split = StringUtils.split(str, "\\|");
                        data.setAccountId(Long.valueOf(split[0]));
                        data.setAccountName(split[1]);
                        data.setAssetsAccount(split[2]);
                        data.setProjectsNumber(split[3]);
                        data.setProjectsName(split[4]);
                        data.setInstitutionCode(split[5]);
                        data.setInstitutionName(split[6]);
                        data.setStatus(split[7]);
                        data.setUpdateType(split[8]);
                        if ("ADD".equalsIgnoreCase(data.getUpdateType())) {
                            insertList.add(data);
                        } else if ("UPDATE".equalsIgnoreCase(data.getUpdateType())) {
                            updateList.add(data);
                        } else {
                            throw new Exception("错误的状态"+data.getUpdateType());
                        }
                    }
                }
                LOGGER.info("待更新数据条数:{}，带插入数据条数:{}", updateList.size(), insertList.size());
                updateList.addAll(insertList);
                saveOrUpdateBatch(updateList);
                successTotal++;
                bufferedReader.close();
            } catch (Exception e) {
                LOGGER.error("解析文件出错:{}", e.getMessage());
                failTotal++;
            }
        }
        LOGGER.info("本次解析成功的文件个数:{}",successTotal);
        LOGGER.info("本次解析失败的文件个数:{}",failTotal);
        LOGGER.info("文件解析结束:{}", LocalDateTime.now());
    }

}
