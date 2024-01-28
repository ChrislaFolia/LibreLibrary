package com.liblib.user.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.liblib.user.model.LibUser;
import com.liblib.user.model.LibUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LibUserService implements ILibUserService {

    @Autowired
    private LibUserRepository userRepo;

    @Autowired
    private PasswordEncoder pwdEncoder;

	@Override
	public boolean registerUser(LibUser userBean) {
		try {
			LibUser resultBean = userRepo.findLibUserByPhoneNumber(userBean.getPhoneNumber());
			if (resultBean==null) {
				// hash(password+salt)
				String hashedPassword = pwdEncoder.encode(userBean.getPassword());
				// Set date
				LocalDate currentDate = LocalDate.now();
//				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			String strDate = currentDate.format(dateFormatter);
				Date utilDate = java.sql.Date.valueOf(currentDate);
				
				userBean.setPassword(hashedPassword);
				userBean.setRegistrationTime(utilDate);
				
				LibUser registerResultBean = userRepo.save(userBean);
				if(registerResultBean!=null) return true;
			}
			return false;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

	@Override
	public LibUser checkLogin(LibUser userBean) {

		try {
			LibUser resultBean = userRepo.findLibUserByPhoneNumber(userBean.getPhoneNumber());
			if (resultBean != null) {
				if (pwdEncoder.matches(userBean.getPassword(), resultBean.getPassword())) {
					return resultBean;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	@Override
	public void updateLastLoginTime(LibUser userBean) {
		try {
		LibUser resultBean = userRepo.findLibUserByPhoneNumber(userBean.getPhoneNumber());
		if (resultBean != null) {
		LocalDate currentDate = LocalDate.now();
		Date utilDate = java.sql.Date.valueOf(currentDate);
		resultBean.setLastLoginTime(utilDate);
			userRepo.save(resultBean);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LibUser findLibUserByPhoneNumber(String phoneNumber) {
		try {
			LibUser resultBean = userRepo.findLibUserByPhoneNumber(phoneNumber);
			if(resultBean!=null) return resultBean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}


	
}
