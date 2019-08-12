package com.ns.check.Repository;

import com.ns.check.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase,Long> {
}
