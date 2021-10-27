package com.Repository;

import Enitities.ArimaDiscoveryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;

import com.Config.AbstractDao;
import IRepository.IArimaDiscoveryRepository;

import java.util.List;

@Repository
@Transactional
public class ArimaDiscoveryRepository extends AbstractDao<ArimaDiscoveryEntity> implements IArimaDiscoveryRepository {
    @Override
    public List<ArimaDiscoveryEntity> getArimaDiscovery() {
        Query query =  currentSession().createQuery("from ArimaDiscoveryEntity");
        List<ArimaDiscoveryEntity> type = query.list();
        return type;
    }
}
