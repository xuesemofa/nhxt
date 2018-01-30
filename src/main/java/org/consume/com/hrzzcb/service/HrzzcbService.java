package org.consume.com.hrzzcb.service;

import org.consume.com.hrzrl.model.HrzrlFxModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HrzzcbService {
    ArrayList<Object> getByIdAndRq(String id, Map<Integer, long[]> map);
}
