package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.UserConnection;
import com.adinfi.seven.presentation.views.util.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

public class DAOUserConnectionImpl extends AbstractDaoImpl<UserConnection> implements DAOUserConnection {

    @Override
    public UserConnection findUserConnectionByUserId(int userId){
        List<UserConnection> list = getHibernateTemplate().find("from UserConnection uc where uc.userId = ? and uc.active = 1", userId);
        if(list == null || list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }
    
    @Override
    public int insertUser(UserConnection userConnectionDTO){
        Session s = super.getSession();
        try {
            s.beginTransaction();
            s.save(userConnectionDTO);
            s.getTransaction().commit();
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(DAOUserConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);
            s.getTransaction().rollback();
            return -1;
        }
    }
    
    
    @Override
    public int updateUserConnection(UserConnection userConnectionDTO ){
        Session s = super.getSession();
        try {
            s.beginTransaction();
            s.update(userConnectionDTO);
            s.getTransaction().commit();
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(DAOUserConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);
            s.getTransaction().rollback();
            return -1;
        }
    }
}
