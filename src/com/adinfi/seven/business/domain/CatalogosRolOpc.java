package com.adinfi.seven.business.domain;

import java.util.List;

/**
 * Created by christian on 28/01/2015.
 */
public class CatalogosRolOpc implements java.io.Serializable {

        Integer rolOpcId = null;
        String role = null;
        CatalogosOpc CatalogosOpc = null;

        /**
         * @return the rolOpcId
         */
        public Integer getRolOpcId() {
            return rolOpcId;
        }

        /**
         * @param rolOpcId the rolOpcId to set
         */
        public void setRolOpcId(Integer rolOpcId) {
            this.rolOpcId = rolOpcId;
        }

        /**
         * @return the role
         */
        public String getRole() {
            return role;
        }

        /**
         * @param role the role to set
         */
        public void setRole(String role) {
            this.role = role;
        }


    public CatalogosOpc getCatalogosOpc() {
        return CatalogosOpc;
    }

    public void setCatalogosOpc(CatalogosOpc catalogosOpc) {
        CatalogosOpc = catalogosOpc;
    }
}