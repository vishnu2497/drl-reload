package com.ns.check.Controller;

import com.ns.check.Model.Purchase;
import com.ns.check.Model.ResultantModel;
import com.ns.check.Model.UserDetails;
import com.ns.check.Repository.PurchaseRepo;
import com.ns.check.Repository.ResultantModelRepo;
import com.ns.check.Repository.UserDetailRepo;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {


    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private UserDetailRepo userRepo;

    @Autowired
    private ResultantModelRepo resultRepo;


    @PostMapping("/purchase")
    public Purchase submitPurchase(@RequestBody Purchase purchaseData) {


        purchaseRepo.save(purchaseData);
        KieServices ks = KieServices.Factory.get();
        KieContainer kcontainer = ks.getKieClasspathContainer();
        KieSession ksession = kcontainer.newKieSession("ksession-rule");


      int  distributorData = purchaseData.getDistributorId();
        System.out.println(distributorData);


//Creating the list

      List<UserDetails> userData = new ArrayList<>();

        userData.add(userRepo.findByDistributorId(distributorData));


     while (distributorData > 0) {
            try {
                System.out.println(userData.size());
                System.out.println(userData.get(userData.size() - 1).getSponserid());
                if (userData.get(userData.size() - 1).getSponserid() > 0) {
                    distributorData = userData.get(userData.size() - 1).getSponserid();
//                    userData.add(userRepo.findByDistributorId(distributorData));

                    UserDetails ud = new UserDetails();
                    ud = userRepo.findByDistributorId(distributorData);
                    ud.setNextLevel(userData.get(userData.size() - 1).getLevel());
                    System.out.println("The next level id is :" + ud.getNextLevel());


                    System.out.println("The user data contains the following value");
                    System.out.println(userData);


                    userData.add(ud);


                } else {
                    distributorData = 0;
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("UserData  is");
        for (int i = 0; i < userData.size(); i++) {
            System.out.println(userData.get(i));
        }


        FactHandle fact1;

        ksession.setGlobal("ccAmount",purchaseData.getCc());
        ksession.setGlobal("orderAmount",purchaseData.getOrderPrice());
        for (int i = 0; i < userData.size(); i++) {

            ResultantModel rm=new ResultantModel();
            ksession.setGlobal("rm",rm);

            fact1 = ksession.insert(userData.get(i));

            int firedRules = ksession.fireAllRules();
            System.out.println("Total rules fired" + firedRules);
            resultantData(rm,purchaseData.getOrderNumber());

                System.gc();
        }

        return purchaseData;
    }

    public void resultantData(ResultantModel rm,String orderNumber)
    {
        rm.setOrderNumber(orderNumber);
        resultRepo.save(rm);

    }

}

