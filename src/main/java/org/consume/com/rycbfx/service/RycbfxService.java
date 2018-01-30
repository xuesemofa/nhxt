package org.consume.com.rycbfx.service;

import org.consume.com.hrzrl.model.HrzrlFxModel;

import java.util.List;
import java.util.Map;

public interface RycbfxService {

    List<HrzrlFxModel> findByRq(Map<Integer, long[]> map);
}
