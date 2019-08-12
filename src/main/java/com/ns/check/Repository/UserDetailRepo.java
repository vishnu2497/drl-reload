package com.ns.check.Repository;

import com.ns.check.Model.UserDetails;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserDetailRepo extends JpaRepository<UserDetails,Long> {

    UserDetails findByDistributorId(int id);

    public static final String data="select distributor_id from userdetails";

    @Query(value = data,nativeQuery = true)
    public List<Integer> findDataAll();


    public static final String data1="select sponserid from userdetails";
    @Query(value = data1,nativeQuery = true)
    public List<Integer> findDataAll1();


    public static final String data2="select level from userdetails";
    @Query(value = data2,nativeQuery = true)
    public List<Integer> findDataLevel();


    List<UserDetails> findByDistributorIdIn(List<Integer> data);



}
