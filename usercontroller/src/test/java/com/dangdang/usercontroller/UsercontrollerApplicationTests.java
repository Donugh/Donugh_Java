package com.dangdang.usercontroller;

import com.alibaba.fastjson.JSON;
import com.dangdang.usercontroller.entity.AndroidMessage;
import com.dangdang.usercontroller.entity.Pam.RanDomAdPam;
import com.dangdang.usercontroller.entity.Rtn.RandomAdRtn;
import com.dangdang.usercontroller.mapper.AdAllocationMapper;
import com.dangdang.usercontroller.mapper.UserMapper;
import com.dangdang.usercontroller.service.impl.UserServiceImpl;
import com.dangdang.usercontroller.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UsercontrollerApplicationTests {
	@Autowired
	private AdAllocationMapper adAllocationMapper;

	@Qualifier("MyCacheManager")
	@Autowired
	CacheManager cacheManager;
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private UserServiceImpl userService;


	@Autowired
	private RestTemplate restTemplate;


	@Autowired
	private UserMapper userMapper;

    private final static String TEST_REDIS = "test_redis";

	@Test
	void contextLoads() {
//		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(new Date());
//		System.out.println(sdf1.format(new Date()));
		AndroidMessage message = new AndroidMessage();
		message.setDeviceUUId("DeviceUUId");
		message.setDeviceBrand("DeviceBrand");
		message.setDeviceId("DeviceId");
		message.setApplicationId("ApplicationId");
		message.setOSVersion("OSVersion");
		message.setAppVersion("AppVersion");
		message.setSign("Sign");
		message.setProvidersName("ProvidersName");
		message.setNetworkType("NetworkType");
		message.setSystemVersion("SystemVersion");
		message.setIsPad(0);
		message.setRequestTime(DateUtil.currentTime());

//		AndroidMessage message1 = (AndroidMessage) redisTemplate.opsForHash().get("AndroidMessage", "5672");
//		System.out.println(message1.toString());
//		for (int i = 0; i < 500000; i++) {
		    // Redis 数据保存测试
			redisTemplate.opsForHash().put("AndroidMessage",6+"",message);
//		}



	}

	@Test
	void mqTest() {
		String queue ="AndroidMessage";
        AndroidMessage message = new AndroidMessage();
        message.setDeviceUUId("DeviceUUId");
        message.setDeviceBrand("DeviceBrand");
        message.setDeviceId("DeviceId");
        message.setApplicationId("ApplicationId");
        message.setOSVersion("OSVersion");
        message.setAppVersion("AppVersion");
        message.setSign("Sign");
        message.setProvidersName("ProvidersName");
        message.setNetworkType("NetworkType");
        message.setSystemVersion("SystemVersion");
        message.setIsPad(0);
        message.setRequestTime(DateUtil.currentTime());
		for (int i = 0; i < 100000; i++) {
			// RabbitMQ测试
			rabbitTemplate.convertAndSend(queue, JSON.toJSON(message));
		}
	}


	@Test
	void mqSave() {
//		AndroidMessage message = new AndroidMessage();
//		message.setDeviceUUId("DeviceUUId");
//		message.setDeviceBrand("DeviceBrand");
//		message.setDeviceId("DeviceId");
//		message.setApplicationId("ApplicationId");
//		message.setOSVersion("OSVersion");
//		message.setAppVersion("AppVersion");
//		message.setSign("Sign");
//		message.setProvidersName("ProvidersName");
//		message.setNetworkType("NetworkType");
//		message.setSystemVersion("SystemVersion");
//		message.setIsPad(0);
//		message.setRequestTime(DateUtil.currentTime());
//		for (int i = 0; i < 500; i++) {
//			// Service测试
//			userService.saveAndroidMsgTest(message);
//		}

	}


	@Test
	void userDev(){
		String url = "http://47.100.161.10:80/saveAndroidMsg";
//		String url = "http://192.168.0.187:80/saveAndroidMsg";
		AndroidMessage message = new AndroidMessage();
		message.setDeviceUUId("DeviceUUId");
		message.setDeviceBrand("获取手机序列号");
		message.setDeviceId("获取手机序列号");
		message.setApplicationId("获取手机序列号");
		message.setOSVersion("获取手机序列号");
		message.setAppVersion("AppVersion");
		message.setSign("Sign");
		message.setProvidersName("ProvidersName");
		message.setNetworkType("NetworkType");
		message.setSystemVersion("SystemVersion");
		message.setIsPad(0);
		message.setRequestTime(DateUtil.currentTime());
//		ResponseEntity<String> str = restTemplate.postForEntity(url, message, String.class);
//		for (int i = 0; i < 500; i++) {
			try {
				restTemplate.postForEntity(url, message, String.class);
			} catch (RestClientException e) {
				e.printStackTrace();
//				break;
//			}
		}
	}




	@Test
	void RedisUtil(){
//		int i = 3;
//	switch (i){
//		case 1:
//			System.out.println("asdf");
//	}
		Map<String,Object> map = new HashMap<>();
		String ttad = "{\"adType\":\"TTAD\",\"adId\":\"950262161\"}";
		String dkad = "{\"adType\":\"DKAD\",\"adId\":\"104\",\"placeTag\":\"videowhotok\"}";
		String ylad = "{\"adType\":\"YLAD\",\"adId\":\"1235\",\"appId\":\"96654\",\"placeTag\":\"wertrthty\"}";
		map.put("ttad",JSON.toJSON(ttad).toString());
		map.put("dkad",JSON.toJSON(dkad).toString());
		map.put("ylad",JSON.toJSON(ylad).toString());
		redisTemplate.opsForHash().putAll("AD",map);

//		Set ad = redisTemplate.opsForHash().keys("AD");
//		System.out.println(ad);
//		ArrayList<String> list = new ArrayList<>(ad);
//		Random random = new Random();
//		int n = random.nextInt(list.size());
//		String s1 = list.get(n);
//		System.out.println(s1);
//		Object ad1 = redisTemplate.opsForHash().get("AD", s1);
//		System.out.println(ad1.toString());
//		for (String s : list) {
//			System.out.println(s);
//		}


	}



	@Test
	void cache(){
		RanDomAdPam ranDomAdPam = new RanDomAdPam();
//		ranDomAdPam.setAppNum(100);
//		List<RandomAdRtn> randomAdRtns = adAllocationMapper.selAppAd(ranDomAdPam);

//		Cache dept = cacheManager.getCache("AA");
//		dept.put("2",ranDomAdPam);
//		List<RandomAdRtn> randomAdRtns = adAllocationMapper.selAppAd(ranDomAdPam);
//		redisTemplate.opsForValue().set("TT:200",randomAdRtns);
		try {
			Object aa = redisTemplate.opsForValue().get("AA::196846");
			System.out.println("-----------object-----------------"+aa);
			List<RandomAdRtn> list = (List<RandomAdRtn>)aa;
			System.out.println("-----list-------"+list);
			if(list != null){
				System.out.println("有值");
			}else{
				System.out.println("没有值");
			}
//			for (RandomAdRtn randomAdRtn : list) {
//
//                System.out.println(randomAdRtn);
//            }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出错了");
		}
//		List range = redisTemplate.opsForList().range("AA::AAD:2", 0, -1);
//		for (Object o : range) {
//			System.out.println(o.toString());
//		}
//		Object o = redisTemplate.opsForList().leftPop("AA::AAD:2");
//		System.out.println(o.toString());

	}

}
