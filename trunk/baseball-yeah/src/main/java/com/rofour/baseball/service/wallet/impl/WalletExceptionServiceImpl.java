package com.rofour.baseball.service.wallet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.dao.wallet.mapper.WalletExceptionMapper;
import com.rofour.baseball.service.wallet.WalletExceptionService;

@Service("walletExceptionService")
public class WalletExceptionServiceImpl implements WalletExceptionService {

	@Autowired
	@Qualifier(value="walletExceptionMapper")
	WalletExceptionMapper walletExceptionMapper;
}
