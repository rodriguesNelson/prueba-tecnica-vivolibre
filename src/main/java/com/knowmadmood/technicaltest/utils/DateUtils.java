package com.knowmadmood.technicaltest.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

	public String fromTimestampToString(Timestamp timestamp) {

		if (Objects.nonNull(timestamp)) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			return sdf.format(new Date(timestamp.getTime()));
		}
		return null;
	}
}
