package com.server.base.service;

import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.userRepository.User;
import com.server.base.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * Refresh 토큰 가져오기
     * @param userNo
     * @return
     */
    @Transactional(readOnly = true)
    public UserDto getRefreshToken(Long userNo){
        return Mapper.modelMapping(userRepository.getUserByUserNo(userNo), new UserDto());
    }

    /**
     * 로그인
     * @param userDto
     * @return
     * @throws ServiceException
     */
    @Transactional(readOnly = true)
    public UserDto signIn(UserDto userDto) throws ServiceException{
        User user = userRepository.getUserByUserIdAndUserLockDateBefore(userDto.getUserId(), LocalDateTime.now())
                .orElseThrow(() ->  new ServiceException(Exceptions.NO_DATA));
        String password = userDto.getPassword();
        String rawPassword = user.getPassword();
        if(!passwordEncoder.matches(password, rawPassword)){
            throw  new ServiceException(Exceptions.NO_DATA);
        }
        return Mapper.modelMapping(user, new UserDto());
    }

    /**
     * 간편 비밀번호 설정
     * @param userDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void easySignUp(UserDto userDto) throws ServiceException {
        User user = userRepository.getUserByUserIdAndUserLockDateBefore(userDto.getUserId(), LocalDateTime.now())
                .orElseThrow(()-> new ServiceException(Exceptions.NO_DATA));
        user.setPasswordSub(passwordEncoder.encode(userDto.getPasswordSub()));
    }

    /**
     * 간편 로그인
     * @param userDto
     * @throws ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    public void easySignIn(UserDto userDto) throws ServiceException {
            User user = userRepository.getUserByUserIdAndUserLockDateBefore(userDto.getUserId(), LocalDateTime.now())
                    .orElseThrow(() -> new ServiceException(Exceptions.NO_DATA));
            String password = userDto.getPasswordSub();
            String rawPassword = user.getPasswordSub();
            if (!passwordEncoder.matches(password, rawPassword)) {
                user.subPasswordFail();
                throw new ServiceException(Exceptions.NO_DATA);
            }
    }

    /**
     * 사용자 등록
     * @param userDto
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public UserDto saveUser(UserDto userDto) throws ServiceException {
           User user = userRepository.getUserByUserId(userDto.getUserId())
                        .orElseGet(User::new);
           if(!Objects.isNull(user)&&user.equals(new User())){
               throw new ServiceException(Exceptions.ALREADY_EXIST);
           }
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user = Mapper.modelMapping(userDto, new User());
            user = userRepository.save(user);
            userDto = Mapper.modelMapping(user, userDto);
        return userDto;
    }

    /**
     * 사용자 비밀번호 변경
     * @param userDto
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDto changePassword(UserDto userDto) throws ServiceException {
        User user = userRepository.getUserByUserIdAndUserLockDateBefore(userDto.getUserId(), LocalDateTime.now())
                .orElseThrow(()->{ return new ServiceException(Exceptions.NO_DATA);});
        if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
            throw  new ServiceException(Exceptions.NO_DATA);
        }
        user.setPassword(userDto.getPasswordNew());
        return Mapper.modelMapping(user, new UserDto());
    }
}
