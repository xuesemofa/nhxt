package org.consume.com.sdrdj.service;

import org.consume.com.sdrdj.model.SdrdjModel;

import java.util.List;

public interface SdrdjService {
    int add(SdrdjModel model);

    int del();

    List<SdrdjModel> finds();
}
