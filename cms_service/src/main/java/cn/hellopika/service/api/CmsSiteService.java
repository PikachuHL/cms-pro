package cn.hellopika.service.api;

import cn.hellopika.service.dto.CmsSiteDto;

public interface CmsSiteService {

    CmsSiteDto getById(int id);

    void update(CmsSiteDto cmsSiteDto);
}
