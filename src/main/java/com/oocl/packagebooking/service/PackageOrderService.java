package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.PackageOrder;
import com.oocl.packagebooking.repository.PackegeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PackageOrderService {

    @Autowired
    PackegeOrderRepository packegeOrderRepository;

    public PackageOrder savePackageOrder(PackageOrder packegeOrder) {
        return packegeOrderRepository.saveAndFlush(packegeOrder);
    }

    public List<PackageOrder> getPackageOrders() {
        return packegeOrderRepository.findAll();
    }

    public List<PackageOrder> getPackageOrdersByState(Integer state){
        return packegeOrderRepository.findAllByState(state);
    }

    public PackageOrder updatePackageOrder(PackageOrder packageOrder) {
        PackageOrder exitedPackageOrder = packegeOrderRepository.findByTrackingNum(packageOrder.getTrackingNum());
        if(exitedPackageOrder.getAppointTime() == null) {
            Date appointTime = packageOrder.getAppointTime();
            int hour = appointTime.getHours();
            if(hour > 8 && hour < 20) {
                exitedPackageOrder.setAppointTime(packageOrder.getAppointTime());
                return packegeOrderRepository.saveAndFlush(exitedPackageOrder);
            }else {
                return null;
            }
        }
        exitedPackageOrder.setState(packageOrder.getState());
        return packegeOrderRepository.saveAndFlush(exitedPackageOrder);
    }
}
