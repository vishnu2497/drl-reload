package com.ns.check.Controller;

import com.ns.check.Model.Purchase;
import com.ns.check.Model.ResultantModel;
import com.ns.check.Repository.PurchaseRepo;
import com.ns.check.Service.Java.PurchaseProcessJava;
import com.ns.check.Service.Drools.PurchaseServiceDrools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class PurchaseController {


    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private PurchaseServiceDrools purchaseServiceDrools;

    @Autowired
    private PurchaseProcessJava purchaseServiceJava;

    @PostMapping("/purchase")
    public String submitPurchase() {


        List<Purchase> purchaseData1 = new ArrayList<>();
        List<ResultantModel> resultSet = new ArrayList<>();



        purchaseData1 = purchaseRepo.findAll();
        purchaseServiceDrools.firstDone();

        purchaseServiceJava.firstDone();

        for (Purchase data : purchaseData1) {



                purchaseServiceDrools.processingMethod(data, resultSet);

//                purchaseServiceJava.processingMethod(data, resultSet);

//            }



        }

        purchaseServiceDrools.dataStore(resultSet);
//        purchaseServiceJava.dataStore(resultSet);
        return "Done from major method";

    }
}


//    For Getting all records


//    @GetMapping(value = "/records")
//    public List<ResultantModel> getAllData()
//    {
//        return resultRepo.findAll();
//    }


//    Get All records by the OrderNumber


//    @GetMapping(value = "/records/{orderNumber}")
//    public List<ResultantModel> getParticularData(@PathVariable("orderNumber") String orderNumber)
//    {
//        return resultRepo.findByOrderNumber(orderNumber);
//    }

//    public void resultantData(ResultantModel rm, String orderNumber) {
//        rm.setOrderNumber(orderNumber);
//
//
//
//        resultRepo.save(rm);
//
//    }
