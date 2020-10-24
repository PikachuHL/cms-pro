package cn.hellopika.service.impl;

import cn.hellopika.dao.mapper.TestMapper;
import cn.hellopika.service.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public int count() {
        return testMapper.count();
    }
}
