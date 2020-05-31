package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Addresses;
import com.lnsf.tmall.pojo.AddressesExample;

import java.util.List;

public interface AddressesService {
    List<Addresses> allAddresses();
    Addresses selectAddressById(Integer aid);
    int  updateAddressById(Addresses addresses);
    int  deleteAddressById(Integer aid);
    int insert(Addresses addresses);
}
