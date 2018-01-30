package org.consume.com.rymj.service;

import org.consume.com.rymj.model.RymjModel;

import java.util.List;

public interface RymjService {
    int set(RymjModel model);

    List<RymjModel> get();
}
