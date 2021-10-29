package com.Services;

import Enitities.ArimaDiscoveryEntity;
import IRepository.IArimaDiscoveryRepository;
import IServices.ISampleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServices implements ISampleServices {

    @Autowired
    IArimaDiscoveryRepository _personTypeRepository;

    public int getArimaInfo() {
        List<ArimaDiscoveryEntity> personTypes= _personTypeRepository.getArimaDiscovery();
        return personTypes.size();
    }
}
