package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.business.domain.RelDisenoSenal;
import com.adinfi.seven.business.domain.TblDisenos;
import com.adinfi.seven.persistence.daos.DAORelDisenoSenal;

import java.util.List;

/**
 * Created by jdominguez on 3/22/16.
 */
public class ServiceRelDisenoSenalImpl implements ServiceRelDisenoSenal {

    private DAORelDisenoSenal daoRelDisenoSenal;

    @Override
    public boolean saveRelDisenoSenal(RelDisenoSenal relDisenoSenal) {
        try {
            daoRelDisenoSenal.save(relDisenoSenal);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RelDisenoSenal> getSenalByDiseno(TblDisenos disenos) {
        return daoRelDisenoSenal.getSenalByDiseno(disenos);
    }

    @Override
    public List<RelDisenoSenal> getDisenoBySenal(CatSenal catSenal) {
        return daoRelDisenoSenal.getDisenoBySenal(catSenal);
    }

    public DAORelDisenoSenal getDaoRelDisenoSenal() {
        return daoRelDisenoSenal;
    }

    public void setDaoRelDisenoSenal(DAORelDisenoSenal daoRelDisenoSenal) {
        this.daoRelDisenoSenal = daoRelDisenoSenal;
    }
}
