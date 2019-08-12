package com.ns.check.Service.Java;


import com.ns.check.Model.Purchase;
import com.ns.check.Model.ResultantModel;
import com.ns.check.Model.UserDetails;
import com.ns.check.Repository.ResultantModelRepo;
import com.ns.check.Repository.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@Service
public class PurchaseProcessJava {

    @Autowired
    private UserDetailRepo userDetailRepo;

    @Autowired
    private ResultantModelRepo resultantModel;

    HashMap<Integer, Integer> hm = new HashMap<>();
    HashMap<Integer, Integer> distLevel = new HashMap<>();


    public void firstDone() {
        List<Integer> distributor = userDetailRepo.findDataAll();
        List<Integer> sponser = userDetailRepo.findDataAll1();
        int distCap = distributor.size();

        for (int i = 0; i < distCap; i++) {
            hm.put(distributor.get(i), sponser.get(i));
        }
//        System.out.println(hm);

        List<Integer> level = userDetailRepo.findDataLevel();

        for (int i = 0; i < distCap; i++) {
            distLevel.put(distributor.get(i), level.get(i));
        }
//        System.out.println(distLevel);


    }

    public String processingMethod(Purchase data, List<ResultantModel> resultSet) {

        int distributorId = data.getDistributorId();
        List<Integer> distributorData = new ArrayList<>();
        distributorData.add(distributorId);

        while (distributorId >= 1) {
            distributorId = hm.get(distributorId);
            distributorData.add(distributorId);
        }
//        System.out.println(distributorData);
        List<UserDetails> dataFromDb = new ArrayList<>();
        dataFromDb = userDetailRepo.findByDistributorIdIn(distributorData);

//        System.out.println(dataFromDb);


        dataProcessing(dataFromDb, data, resultSet);
        distributorData.clear();


        return "Done in processing Method";

    }

    public void dataProcessing(List<UserDetails> data, Purchase purchaseData, List<ResultantModel> resultSet) {
        Collections.reverse(data);
        int size = data.size();
        List<UserDetails> userData = new ArrayList<>();
        System.out.println("******");
        UserDetails ud = new UserDetails();

        if (size == 1) {
            ud = data.get(0);
            ud.setNextLevel(0);
            userData.add(ud);
            System.out.println(userData);
        } else {
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    ud = data.get(i);
                    ud.setNextLevel(0);
                    userData.add(ud);
                } else if (data.get(i).getDistributorId() == 1) {
                    ud = data.get(i);
//                ud.setNextLevel(userData.get(size-1).getLevel());
//                userData.add(ud);
                    ud.setNextLevel(userData.get(userData.size() - 1).getLevel());
                    userData.add(ud);
                } else {
                    ud = data.get(i);
                    ud.setNextLevel(userData.get(userData.size() - 1).getLevel());
                    userData.add(ud);
                }

            }
        }

        for (UserDetails finishedData : userData) {
            commisionCalculation(finishedData, purchaseData, resultSet);
//            System.out.println(finishedData);
        }


//        userData.forEach(data1->commisionCalculation(data1,purchaseData,resultSet));

//        userData.parallelStream().forEach(data1->commisionCalculation(data1,purchaseData,resultSet));
    }

    public void commisionCalculation(UserDetails userDetails, Purchase purchaseData, List<ResultantModel> resultSet) {

        float orderAmount = purchaseData.getOrderPrice();
        float ccAmount = purchaseData.getCc();
        ResultantModel rm = new ResultantModel();

        rm.setDistributorId(userDetails.getDistributorId());
        rm.setDistributorLevel(userDetails.getLevel());
        rm.setSponserLevel(userDetails.getSponserLevel());
        rm.setOrderNumber(purchaseData.getOrderNumber());


        int currentLevel = userDetails.getLevel();

        switch (currentLevel) {
            case 1:
                int nextLevel = userDetails.getNextLevel();
                switch (nextLevel) {
                    case 0:
                        System.out.println("**Self purchase by Novus");
                        float directDiscount = (15 * orderAmount) / 100;
                        rm.setDirectDiscount(directDiscount);
                        rm.setPersonalCc(ccAmount);
                        break;

                    case 1:
                        System.out.println("**Bonus for Novus by novus and his downlines");
                        rm.setNonManagerCc(ccAmount);
                        break;


                }
                break;

            case 2:
                nextLevel = userDetails.getNextLevel();
                switch (nextLevel) {
                    case 0:
                        System.out.println("--Self purchase by assistant supervisor");
                        System.out.println("He is having 30%Direct Discount and 5 % personal Discount");
                        System.out.println("Personal CC->" + ccAmount);

                        float directDiscount = (30 * orderAmount) / 100;
                        System.out.println("Direct Discount Amount is ->" + directDiscount);

                        float personalDiscount = (5 * orderAmount) / 100;
                        System.out.println("Personal Discount Amount is ->" + personalDiscount);
                        rm.setPersonalCc(ccAmount);
                        rm.setDirectDiscount(directDiscount);
                        rm.setPersonalDiscount(personalDiscount);
                        break;

                    case 1:
                        System.out.println("--Bonus for assistant supervisor by novus and his downlines");
                        System.out.println("He is having 15% Novus profit and 5 % Novus Bonus");

                        float novusProfit = (15 * orderAmount) / 100;
                        System.out.println("Novus Profit is -> " + novusProfit);

                        float novusBonus = (5 * orderAmount) / 100;
                        System.out.println("Novus Bonus is -> " + novusBonus);
                        System.out.println("Novus CC is ->" + ccAmount);
                        System.out.println("Non Manager CC->" + ccAmount);
                        rm.setNovusProfit(novusProfit);
                        rm.setNovusCc(ccAmount);
                        rm.setNovusBonus(novusBonus);
                        rm.setNonManagerCc(ccAmount);
                        break;

                    case 2:
                        System.out.println("--Only case credits By AssistantSupervisor for Assistant Aupervisor");
                        System.out.println("Non Mnager CC->" + ccAmount);
                        rm.setNonManagerCc(ccAmount);
                        break;

                }
                break;

            case 3:
                nextLevel = userDetails.getNextLevel();
                switch (nextLevel) {
                    case 0:
                        System.out.println("--Bonus for supervisor for his self purchase");

                        System.out.println("He is having 30%Direct Discount and 8 % personal Discount");
                        System.out.println("Personal  CC is ->" + ccAmount);

                        float directDiscount = (30 * orderAmount) / 100;
                        System.out.println("Direct Discount Amount is ->" + directDiscount);

                        float personalDiscount = (8 * orderAmount) / 100;
                        System.out.println("Personal Discount Amount is ->" + personalDiscount);

                        rm.setPersonalCc(ccAmount);
                        rm.setDirectDiscount(directDiscount);
                        rm.setPersonalDiscount(personalDiscount);
                        break;
                    case 1:
                        System.out.println("--Bonus for supervisor by novus downlines");

                        System.out.println("He is having 15% Novus profit and 8 % Novus Bonus");

                        float novusProfit = (15 * orderAmount) / 100;
                        System.out.println("Novus Profit is -> " + novusProfit);
                        float novusBonus = (8 * orderAmount) / 100;
                        System.out.println("Novus Bonus is -> " + novusBonus);
                        System.out.println("Novus CC is ->" + ccAmount);

                        System.out.println("Non Manager CC->" + ccAmount);

                        rm.setNovusBonus(novusBonus);
                        rm.setNovusProfit(novusProfit);
                        rm.setNonManagerCc(ccAmount);
                        rm.setNovusCc(ccAmount);
                        break;
                    case 2:
                        System.out.println("--Bonus for supervisor by Asst.supervisor downlines");

                        System.out.println("Only 3% of violume Bonus");
                        float volumeBonus = (3 * orderAmount) / 100;
                        System.out.println("Volume Bonus" + volumeBonus);
                        System.out.println("Non Mnager CC->" + ccAmount);
                        rm.setVolumeBonus(volumeBonus);
                        rm.setNonManagerCc(ccAmount);
                        break;
                    case 3:
                        System.out.println("--Bonus for supervisor by supervisor downlines");

                        System.out.println("Only case credits");
                        System.out.println("Non Mnager CC->" + ccAmount);
                        rm.setNonManagerCc(ccAmount);
                        break;

                }
                break;


            case 4:
                nextLevel = userDetails.getNextLevel();
                switch (nextLevel) {
                    case 0:
                        System.out.println("--Self purchase by Asst.manager");


                        System.out.println("He is eligible for 30%direct discount 13% personal discount");
                        System.out.println("Personal  CC is ->" + ccAmount);
                        float directDiscount = (30 * orderAmount) / 100;
                        System.out.println("Direct Discount Amount is ->" + directDiscount);
                        float personalDiscount = (13 * orderAmount) / 100;
                        System.out.println("Personal Discount Amount is ->" + personalDiscount);

                        rm.setPersonalCc(ccAmount);
                        rm.setDirectDiscount(directDiscount);
                        rm.setPersonalDiscount(personalDiscount);
                        break;
                    case 1:
                        System.out.println("--Bonus for Assistent manager by novus and his downlines");
                        System.out.println("He is eligible for 15% Novus Profit  13% Novus Bonus");
                        float novusProfit = (15 * orderAmount) / 100;
                        System.out.println("Novus Profit is -> " + novusProfit);

                        float novusBonus = (13 * orderAmount) / 100;
                        System.out.println("Novus Bonus is -> " + novusBonus);
                        System.out.println("Novus CC is ->" + ccAmount);
                        System.out.println("Non Manager CC->" + ccAmount);


                        rm.setNovusBonus(novusBonus);
                        rm.setNovusProfit(novusProfit);
                        rm.setNonManagerCc(ccAmount);
                        rm.setNovusCc(ccAmount);
                        break;
                    case 2:
                        System.out.println("--Bonus for Assistent manager by asstsuperviser downlines");
                        System.out.println("8 % volume Bonus");

                        float volumeBonus = (8 * orderAmount) / 100;
                        System.out.println("Volume Bonus" + volumeBonus);
                        System.out.println("Non Mnager CC->" + ccAmount);

                        rm.setVolumeBonus(volumeBonus);
                        rm.setNonManagerCc(ccAmount);
                        break;
                    case 3:
                        System.out.println("--Bonus for Assistent manager by supervisor downlines");
                        System.out.println("Only 5% of violume Bonus");
                        volumeBonus = (5 * orderAmount) / 100;
                        System.out.println("Volume Bonus" + volumeBonus);
                        System.out.println("Non Mnager CC->" + ccAmount);

                        rm.setVolumeBonus(volumeBonus);
                        rm.setNonManagerCc(ccAmount);
                        break;
                    case 4:
                        System.out.println("--Bonus for Assistent manager by Assistent manager");
                        System.out.println("Only Case Credits");
                        System.out.println("Non Manager CC->" + ccAmount);
                        rm.setNonManagerCc(ccAmount);
                        break;

                }
                break;

            case 5:
                nextLevel = userDetails.getNextLevel();
                switch (nextLevel) {
                    case 0:
                        System.out.println("--Self Purchase By Manager");


                        System.out.println("He is eligible for 30%direct discount 18% personal discount");
                        System.out.println("Personal  CC is ->" + ccAmount);
                        float directDiscount = (30 * orderAmount) / 100;
                        System.out.println("Direct Discount Amount is ->" + directDiscount);
                        float personalDiscount = (18 * orderAmount) / 100;
                        System.out.println("Personal Discount Amount is ->" + personalDiscount);

                        rm.setPersonalCc(ccAmount);
                        rm.setDirectDiscount(directDiscount);
                        rm.setPersonalDiscount(personalDiscount);
                        break;

                    case 1:
                        System.out.println("--Bonus for Manager by Novus  and his downlines");

                        System.out.println("He is eligible for 15% Novus Profit 18% Novus Bonus ");

                        float novusProfit = (15 * orderAmount) / 100;
                        System.out.println("Novus Profit is -> " + novusProfit);
                        float novusBonus = (18 * orderAmount) / 100;
                        System.out.println("Novus Bonus is -> " + novusBonus);
                        System.out.println("Novus CC is ->" + ccAmount);
                        System.out.println("Non Manager CC->" + ccAmount);


                        rm.setNovusBonus(novusBonus);
                        rm.setNovusProfit(novusProfit);
                        rm.setNonManagerCc(ccAmount);
                        rm.setNovusCc(ccAmount);
                        break;
                    case 2:
                        System.out.println("--Bonus for Manager by Assistant.supervisor and his downlines");

                        System.out.println("He is eligible for 13% Volume Bonus");
                        float volumeBonus = (13 * orderAmount) / 100;
                        System.out.println("Volume Bonus is ->" + volumeBonus);
                        System.out.println("Non Manager   CC is ->" + ccAmount);
                        rm.setVolumeBonus(volumeBonus);
                        rm.setNonManagerCc(ccAmount);
                        break;
                    case 3:
                        System.out.println("--Bonus for Manager by supervisor and his downlines");

                        System.out.println("He is eligible for 10% Volume Bonus");

                        volumeBonus = (10 * orderAmount) / 100;
                        System.out.println("Volume Bonus is ->" + volumeBonus);
                        System.out.println("Non Manager   CC is ->" + ccAmount);
                        rm.setVolumeBonus(volumeBonus);
                        rm.setNonManagerCc(ccAmount);
                        break;
                    case 4:
                        System.out.println("--Bonus for Manager by Assistant.Manager and his downlines");

                        System.out.println("He is eligible for 5% Volume Bonus");
                        volumeBonus = (5 * orderAmount) / 100;
                        System.out.println("Volume Bonus is ->" + volumeBonus);
                        System.out.println("Non Manager   CC is ->" + ccAmount);

                        rm.setVolumeBonus(volumeBonus);
                        rm.setNonManagerCc(ccAmount);
                        break;
                    case 5:
                        System.out.println("--Bonus for  manager by manage and his downlines");

                        System.out.println("Only Case Credits is available");
                        System.out.println("Non Manager   CC is ->" + ccAmount);

                        rm.setNonManagerCc(ccAmount);
                        break;
                }

                break;


        }

        if (rm.getDistributorId() > 1) {
            int sponserLevel = distLevel.get(userDetails.getSponserid());
            rm.setSponserLevel(sponserLevel);
        } else {
            rm.setSponserLevel(0);
        }
        resultSet.add(rm);


    }

    public void dataStore(List<ResultantModel> data) {

        resultantModel.saveAll(data);
//        data.parallelStream().forEach(x->resultantModel.save(x));


//        new Thread() {
//            public void run() {
//                resultRepo.saveAll(data);
//            }
//        }.start();


    }

}



