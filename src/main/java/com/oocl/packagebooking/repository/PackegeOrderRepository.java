package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.PackageOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackegeOrderRepository extends JpaRepository<PackageOrder, Long> {

    List<PackageOrder> findAllByState(Integer state);

    PackageOrder findByTrackingNum(String trackingNum);
}
