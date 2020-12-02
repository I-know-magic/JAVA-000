package com.example.readwriteseparationv1.repository;

import com.example.readwriteseparationv1.domain.DUser;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface DynamicUserRepository extends JpaRepository<DUser, BigInteger> {


}
