package com._520it.crm.service.impl;

import com._520it.crm.domain.Log;
import com._520it.crm.mapper.LogMapper;
import com._520it.crm.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 如果使用idea，在类名上右键 goto 即可自动创建测试类
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper LogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return LogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Log record) {
        return LogMapper.insert(record);
    }

    @Override
    public Log selectByPrimaryKey(Long id) {
        return LogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Log> selectAll() {
        return LogMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Log record) {
        return LogMapper.updateByPrimaryKey(record);
    }

}
