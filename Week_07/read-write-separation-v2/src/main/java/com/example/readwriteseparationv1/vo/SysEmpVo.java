package com.example.readwriteseparationv1.vo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@Data
public class SysEmpVo {
    @Id
    BigInteger userId;

    String loginName;
    String name;
    String addr;
    String deptName;

}
