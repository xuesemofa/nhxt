package org.consume.com.hrzcbfx.service;

import org.consume.com.hrzcbfx.model.HrzcbfxModel;

import java.util.List;

public interface HrzcbfxService {
    List<HrzcbfxModel> queryByCompany(String id);
}
