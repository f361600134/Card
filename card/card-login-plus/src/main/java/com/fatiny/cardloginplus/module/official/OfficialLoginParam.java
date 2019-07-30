package com.fatiny.cardloginplus.module.official;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.fatiny.cardloginplus.module.core.base.AbstractLoginParam;

public class OfficialLoginParam extends AbstractLoginParam{
	
	//Noting...
	
	@Override
	public String getInputUname() {
		if (StringUtils.isBlank(super.getInputUname())) {
			super.setInputUname(createUsername());
		}
		return super.getInputUname();
	}
	
	@Override
	public Integer getCh() {
		if (super.getCh() == null) {
			super.setCh(99);
		}
		return super.getCh();
	}

	public static String createUsername()
	{
		Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(currentTime);
        //32位UUID
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid+dateString+99;
	}

}
