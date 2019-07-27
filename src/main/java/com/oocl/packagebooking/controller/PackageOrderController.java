package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.PackageOrder;
import com.oocl.packagebooking.service.PackageOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packageOrders")
public class PackageOrderController {

    @Autowired
    PackageOrderService packageOrderService;

    @PostMapping
    public PackageOrder addPackageOrder(@RequestBody PackageOrder packegeOrder) {
        return packageOrderService.savePackageOrder(packegeOrder);
    }

    @GetMapping
    public List<PackageOrder> viewPackageOrdersByState(@RequestParam(required = false) Integer state) {
        return state == null ? packageOrderService.getPackageOrders() : packageOrderService.getPackageOrdersByState(state);
    }

    @PutMapping
    public PackageOrder updatePackageOrder(@RequestBody PackageOrder packageOrder) {
        return packageOrderService.updatePackageOrder(packageOrder);
    }


}
