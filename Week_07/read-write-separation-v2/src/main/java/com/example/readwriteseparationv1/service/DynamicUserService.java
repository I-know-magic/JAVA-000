package com.example.readwriteseparationv1.service;

import com.example.readwriteseparationv1.domain.DUser;
import com.example.readwriteseparationv1.exception.ServiceException;
import com.example.readwriteseparationv1.repository.DynamicUserRepository;
import com.example.readwriteseparationv1.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
@Transactional
@Slf4j
public class DynamicUserService {
    @Autowired
    protected DynamicUserRepository dynamicUserRepository;

    @Transactional(readOnly=true)
    public DUser editPrimary(BigInteger id) {
        DUser user=null;
        try {
            user=dynamicUserRepository.findById(id).get();
        }catch (Exception e) {
            // TODO: handle exception
            log.error("user:编辑错误 {}", e.getMessage());
            throw new ServiceException(ResultCode.INTERNAL_SERVER_QUERY_ERROR.getCode(), e.getMessage());

        }
        return user;

    }
    @Transactional(readOnly=true)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)，
    public DUser editSecond(BigInteger id) {
        DUser user=null;
        try {
            user=dynamicUserRepository.findById(id).get();
        }catch (Exception e) {
            // TODO: handle exception
            log.error("user:编辑错误 {}", e.getMessage());
            throw new ServiceException(ResultCode.INTERNAL_SERVER_QUERY_ERROR.getCode(), e.getMessage());

        }
        return user;

    }
    public BigInteger delete(BigInteger id) {
        try {
            dynamicUserRepository.deleteById(id);
        }catch (Exception e) {
            // TODO: handle exception
            log.error("user:删除错误 {}", e.getMessage());
            throw new ServiceException(ResultCode.INTERNAL_SERVER_DELETE_ERROR.getCode(), e.getMessage());
        }
        return id;
    }}
