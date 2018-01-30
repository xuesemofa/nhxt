package org.consume.com.hrzddhpm.service;

import org.consume.com.hrzddhpm.model.HrzddhpmModel;
import org.consume.com.sltj.model.SltjModel;

import java.util.List;

public interface HrzddhpmService {
    List<SltjModel> findHrzddhpm( List<String> object);
}
