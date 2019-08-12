package com.ns.check.Repository;

import com.ns.check.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailRepo extends JpaRepository<UserDetails,Long> {



    UserDetails findByDistributorId(int id);
}
