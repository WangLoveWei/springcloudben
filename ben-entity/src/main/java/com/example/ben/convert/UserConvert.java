package com.example.ben.convert;

import com.example.ben.model.User;
import com.example.ben.param.user.RegisterParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: wangben
 * @Description: User的转换
 * @Date: 2020/8/10 17:22
 * @Version: 1.0
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({
    })
    List<User> convertRegisters(List<RegisterParam> patient);

    @Mappings({
    })
    User convertRegister(RegisterParam patient);
}
