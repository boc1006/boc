package com.boc.nio06;

public class SayHelloBean implements SayHello {

	@Override
	public String sayHello() {
		return "Welcome to RPC!";
	}

	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Integer sum(Integer x, Integer y) {
		return x+y;
	}

	@Override
	public Long test2(Long x, Long y) {
		return x*y;
	}


}
