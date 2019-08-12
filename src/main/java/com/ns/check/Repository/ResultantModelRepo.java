package com.ns.check.Repository;

import com.ns.check.Model.ResultantModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultantModelRepo extends JpaRepository<ResultantModel,Long> {

    List<ResultantModel> findByOrderNumber(String orderNumber);
}
