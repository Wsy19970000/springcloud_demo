package com.java.service.impl;

import com.java.mapper.LoginMapper;
import com.java.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class LoginServiceImpl implements com.java.service.LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public boolean getUser(String username, String psw){
        return loginMapper.selectUser(username,psw)>=1;
    }


}
