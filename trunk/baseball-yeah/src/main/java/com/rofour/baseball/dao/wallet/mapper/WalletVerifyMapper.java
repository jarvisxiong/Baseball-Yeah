package com.rofour.baseball.dao.wallet.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.wallet.WalletVerifyInfo;

@Named("walletVerifyMapper")
public interface WalletVerifyMapper {
    List<WalletVerifyInfo> selectWalletVerify(WalletVerifyInfo userInfo);
    
    WalletVerifyInfo selectWalletUserInfo(WalletVerifyInfo userInfo);
    
    int updatePuserVerify(WalletVerifyInfo userInfo);
    
    int updateExpUserVerify(WalletVerifyInfo userInfo);
    
    int updateAcctUserVerifyStatus(Long userId);
    
}