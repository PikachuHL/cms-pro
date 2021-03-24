package cn.hellopika.service.impl;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import cn.hellopika.context.utils.UtilsHttp;
import cn.hellopika.core.foundation.BasePage;
import cn.hellopika.dao.entity.CmsUserEntity;
import cn.hellopika.dao.mapper.CmsUserMapper;
import cn.hellopika.service.api.CmsUserService;
import cn.hellopika.service.converter.CmsUserConverter;
import cn.hellopika.service.dto.CmsUserDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsUserServiceImpl implements CmsUserService {

    @Autowired
    private CmsUserMapper cmsUserMapper;

    @Override
    public CmsUserDto findByUsername(String username) {

        CmsUserEntity cmsUserEntity = cmsUserMapper.findByUsername(username);
        // 把 entity 转换成 dto
        CmsUserDto cmsUserDto = CmsUserConverter.CONVERTER.entityToDto(cmsUserEntity);

        return cmsUserDto;
    }


    @Override
    public BasePage<CmsUserDto> getPage(CmsUserDto dto) {
        // 获取前端传来的 分页相关信息
        UtilsHttp.MyPageInfo pageInfo = UtilsHttp.getPageInfo();

        // 使用 PageHelper 执行分页操作
        Page<CmsUserDto> page = PageHelper.startPage(pageInfo.getPageCurrent(), pageInfo.getPageSize()).doSelectPage(() -> CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.getPage(CmsUserConverter.CONVERTER.dtoToEntity(dto))));

        return new BasePage<>(page.getTotal(), page.getResult());
    }

    @Override
    public void save(CmsUserDto dto) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(CmsUserDto cmsUserDto) {
        cmsUserMapper.update(CmsUserConverter.CONVERTER.dtoToEntity(cmsUserDto));
    }

    @Override
    public CmsUserDto selectById(Integer id) {
        return null;
    }

    @Override
    public List<CmsUserDto> selectAll() {
        return CmsUserConverter.CONVERTER.entityToDto(cmsUserMapper.selectAll());
    }
}
