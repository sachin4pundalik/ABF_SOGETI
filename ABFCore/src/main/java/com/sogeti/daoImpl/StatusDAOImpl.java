package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.StatusDAO;
import com.sogeti.db.models.Status;

@Component("stayDAO")
public class StatusDAOImpl extends GenericDaoImpl<Status> implements StatusDAO {

}