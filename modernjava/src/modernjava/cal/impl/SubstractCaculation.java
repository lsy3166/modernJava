package modernjava.cal.impl;

import modernjava.cal.CacultatorSevice;

public class SubstractCaculation implements CacultatorSevice {

	@Override
	public int caculate(int num1, int num2) {
		return num1 - num2;
	}

}
