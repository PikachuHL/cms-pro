package cn.hellopika.core.foundation;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDto<PK extends Serializable> implements Serializable {
    private PK id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
