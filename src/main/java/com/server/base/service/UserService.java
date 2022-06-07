package com.server.base.service;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.userRepository.User;
import com.server.base.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


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
    public String getRefreshToken(Long userNo){
        return userRepository.getUserByUserNo(userNo).getAuthEntity().getRefreshToken();
    }

    /**
     * 로그인
     * @param userDto
     * @return
     * @throws ServiceException
     */
    @Transactional(readOnly = true)
    public UserDto signIn(UserDto userDto) throws ServiceException{
        User user = userRepository.getUserByUserId(userDto.getUserId())
                .orElseThrow(() ->  new ServiceException(Exceptions.NO_DATA));
        String password = userDto.getPassword();
        String rawPassword = user.getPassword();
        if(!passwordEncoder.matches(password, rawPassword)){
            throw  new ServiceException(Exceptions.NO_DATA);
        }
        return Mapper.modelMapping(user, new UserDto());
    }

    /**
     * 간편 로그인
     * @param userDto
     * @throws ServiceException
     */
    @Transactional(readOnly = true)
    public void easySignIn(UserDto userDto) throws ServiceException {
            User user = userRepository.getUserByUserIdAndUserLockDateBefore(userDto.getUserId(), LocalDateTime.now())
                    .orElseThrow(() -> new ServiceException(Exceptions.NO_DATA));
            String password = userDto.getPasswordSub();
            String rawPassword = user.getPasswordSub();
            if (!passwordEncoder.matches(password, rawPassword)) {
                user.subPasswordFail();
//                save?
                throw new ServiceException(Exceptions.NO_DATA);
            }
    }

    /**
     * 사용자 등록
     * @param userDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDto saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = Mapper.modelMapping(userDto, new User());
        user = userRepository.save(user);
        user.setRefreshToken(TokenManager.refreshEncrypt(user.getUserNo()));
        userDto = Mapper.modelMapping(user, userDto);
        return userDto;
    }

}
