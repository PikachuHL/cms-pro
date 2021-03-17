package cn.hellopika.service.impl;

import cn.hellopika.service.dto.CmsPermissionDto;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class CmsPermissionServiceImplTest {

    @Test
    public void test(){
        List<CmsPermissionDto> permissionList = createData();

        // 新建map用于存放 id 和 id所对应的dto
        Map<Integer, CmsPermissionDto> permissionMap = new HashMap<>();

        // 仅用于存放 顶层菜单
        List<CmsPermissionDto> top = new ArrayList<>();

        Integer excludeId = 3;

        permissionList.forEach(x->{
            Integer id = x.getId();
            if(id.compareTo(excludeId) == 0){
                return;
            }

            permissionMap.put(id, x);

            Integer parentId = x.getParentId();

            // 只有顶级菜单才放到top里面
            if(parentId == 0){
                top.add(x);
            }else {
                CmsPermissionDto parentDto = permissionMap.get(parentId);
                if(Objects.isNull(parentDto)){
                    return;
                }

                List<CmsPermissionDto> children = parentDto.getChildren();

                if(CollectionUtils.isEmpty(children)){
                    children = new ArrayList<>();
                }
                children.add(x);

                parentDto.setChildren(children);
            }
        });

        log.info("success");
    }


    // 自己创建一些数据, 用于测试
    private List<CmsPermissionDto> createData(){
        List<CmsPermissionDto> cmsPermissionDtoList = new ArrayList<>();

        CmsPermissionDto cmsPermissionDto1 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto2 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto3 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto4 = new CmsPermissionDto();

        cmsPermissionDto1.setName("测试1");
        cmsPermissionDto2.setName("测试2");
        cmsPermissionDto3.setName("测试3");
        cmsPermissionDto4.setName("测试4");

        cmsPermissionDto1.setId(1);
        cmsPermissionDto2.setId(2);
        cmsPermissionDto3.setId(3);
        cmsPermissionDto4.setId(4);

        cmsPermissionDto1.setParentId(0);
        cmsPermissionDto2.setParentId(1);
        cmsPermissionDto3.setParentId(2);
        cmsPermissionDto4.setParentId(3);

        cmsPermissionDtoList.add(cmsPermissionDto1);
        cmsPermissionDtoList.add(cmsPermissionDto2);
        cmsPermissionDtoList.add(cmsPermissionDto3);
        cmsPermissionDtoList.add(cmsPermissionDto4);

        return cmsPermissionDtoList;

    }


}